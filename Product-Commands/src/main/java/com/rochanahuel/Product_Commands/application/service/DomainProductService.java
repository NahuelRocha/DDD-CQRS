package com.rochanahuel.Product_Commands.application.service;

import com.rochanahuel.Product_Commands.application.dto.business.CreateProductRequest;
import com.rochanahuel.Product_Commands.application.dto.business.ProductPropsToUpdate;
import com.rochanahuel.Product_Commands.application.dto.business.UpdateProductRequest;
import com.rochanahuel.Product_Commands.application.dto.kafka.DeleteProductEvent;
import com.rochanahuel.Product_Commands.application.dto.business.ProductDto;
import com.rochanahuel.Product_Commands.application.dto.kafka.UpdateProductEvent;
import com.rochanahuel.Product_Commands.application.mapper.ProductMapper;
import com.rochanahuel.Product_Commands.domain.entity.Product;
import com.rochanahuel.Product_Commands.domain.exceptions.productException.EmptyUpdatePropertiesException;
import com.rochanahuel.Product_Commands.domain.exceptions.productException.InvalidStockException;
import com.rochanahuel.Product_Commands.domain.exceptions.productException.ProductNotFoundException;
import com.rochanahuel.Product_Commands.domain.exceptions.productException.SkuIsInUseException;
import com.rochanahuel.Product_Commands.domain.repository.ProductRepository;
import com.rochanahuel.Product_Commands.domain.usecases.ProductService;
import com.rochanahuel.Product_Commands.domain.valueObjects.Sku;
import com.rochanahuel.Product_Commands.infraestructure.kafka.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DomainProductService implements ProductService {

    private final ProductRepository productRepository;
    private final EventService eventService;

    @Override
    public ProductDto createProduct(CreateProductRequest createProductRequest) {

        isSkuInUse(createProductRequest.skuId());
        validateStock(createProductRequest.stock());

        var newProduct = Product.createProduct(createProductRequest);

        productRepository.save(newProduct);

        eventService.newProductEvent("newProduct",
                UUID.randomUUID(),
                ProductMapper.mapToProductEvent(createProductRequest));

        return ProductMapper.mapToProductDto(newProduct);
    }

    @Override
    public void deleteProduct(Long productId) {

        var existProductById = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        String deleteProductWithSku = existProductById.getSkuId().skuId();

        productRepository.delete(productId);

        eventService.deleteProductEvent("deleteProduct",
                UUID.randomUUID(),
                new DeleteProductEvent(deleteProductWithSku));
    }

    @Override
    public void updateProduct(Long productId, UpdateProductRequest updates) {

        Map<ProductPropsToUpdate, Object> updatePropsMap = updates.updatesProperties();

        if (updatePropsMap.isEmpty()){
            throw new EmptyUpdatePropertiesException("No properties to update.");
        } else updates.validate();

        var updateProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        Map<String, Object> updateEventProps = new HashMap<>();

        updatePropsMap.forEach((prop, value) -> {
            prop.updateProperty(updateProduct, value);
            updateEventProps.put(prop.name(), value);
        });

        productRepository.save(updateProduct);

        UpdateProductEvent<Map<String, Object>> updateProductEvent = new UpdateProductEvent<>();
        updateProductEvent.setSku(updateProduct.getSkuId().skuId());
        updateProductEvent.setUpdateValue(updateEventProps);


        eventService.updateProductEvent("updateProduct",
                UUID.randomUUID(),
                updateProductEvent);
    }

    private void isSkuInUse(String skuId){
        if (skuId == null) throw new SkuIsInUseException("You must include the Sku field in the request.");
        Sku sku = new Sku(skuId);
        var isSkuInUse = productRepository.isSkuInUse(sku);
        if (isSkuInUse){
            throw new SkuIsInUseException("Sku: " + sku.skuId() + " is in use.");
        }
    }

    private void validateStock(Integer stock){
        if (stock == null)
            throw new InvalidStockException("You must include the Stock field in the request.");
        if (stock < 0)
            throw new InvalidStockException("The initial stock must be greater than or equal to 0");
    }
}
