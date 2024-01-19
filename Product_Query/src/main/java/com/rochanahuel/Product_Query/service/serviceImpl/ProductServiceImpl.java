package com.rochanahuel.Product_Query.service.serviceImpl;

import com.rochanahuel.Product_Query.dto.ProductDto;
import com.rochanahuel.Product_Query.exceptions.ProductNotFoundException;
import com.rochanahuel.Product_Query.kafka.dto.DeleteProductEvent;
import com.rochanahuel.Product_Query.kafka.dto.NewProductEvent;
import com.rochanahuel.Product_Query.kafka.dto.UpdateProductEvent;
import com.rochanahuel.Product_Query.mapper.ProductMapper;
import com.rochanahuel.Product_Query.model.Product;
import com.rochanahuel.Product_Query.repository.ProductRepository;
import com.rochanahuel.Product_Query.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final MongoTemplate mongoTemplate;


    @Override
    public void saveProduct(NewProductEvent newProductEvent) {

        Product newProduct = mapper.mapNewEventToProduct(newProductEvent);
        repository.save(newProduct);
    }

    @Override
    public void deleteProduct(DeleteProductEvent deleteProduct) {

        repository.deleteById(deleteProduct.getSkuId());
    }

    @Override
    public void updateProduct(UpdateProductEvent updateProduct) {

        var findProductToUpdate = repository.findById(updateProduct.getSku())
                .orElseThrow(()->
                        new ProductNotFoundException("Product with sku: "
                                + updateProduct.getSku() + " not found."));

        var sku = findProductToUpdate.getSkuId();
        var updates = updateProduct.getUpdateValue();

        var query = new Query(Criteria.where("skuId").is(sku));
        var update = new Update();

        updates.forEach((key,value) -> update.set(key.toLowerCase(), value));

        Product productUpdate = mongoTemplate.findAndModify(
                query,
                update,
                FindAndModifyOptions.options().returnNew(true),
                Product.class
        );

        log.info("update product {}", productUpdate);

    }
    @Override
    public ProductDto findProductById(String skuId) {

        var getProduct = repository.findById(skuId)
                .orElseThrow(()-> new ProductNotFoundException("Product not found with SKU: " + skuId));

        return mapper.mapProductToProductDto(getProduct);
    }

    @Override
    public List<ProductDto> findAllProducts() {

        List<ProductDto> result = repository.findAll()
                .stream()
                .map(mapper::mapProductToProductDto)
                .toList();

        if (result.isEmpty()) throw new ProductNotFoundException("No products found in the database.");

        return result;
    }
}
