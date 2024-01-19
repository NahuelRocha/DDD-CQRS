package com.rochanahuel.Product_Commands.application.service;

import com.rochanahuel.Product_Commands.application.dto.business.CreateProductRequest;
import com.rochanahuel.Product_Commands.application.dto.business.ProductDto;
import com.rochanahuel.Product_Commands.application.dto.kafka.DeleteProductEvent;
import com.rochanahuel.Product_Commands.application.dto.kafka.NewProductEvent;
import com.rochanahuel.Product_Commands.application.mapper.ProductMapper;
import com.rochanahuel.Product_Commands.domain.entity.Product;
import com.rochanahuel.Product_Commands.domain.exceptions.productException.ProductNotFoundException;
import com.rochanahuel.Product_Commands.domain.repository.ProductRepository;
import com.rochanahuel.Product_Commands.domain.valueObjects.Sku;
import com.rochanahuel.Product_Commands.infraestructure.kafka.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DomainProductServiceTest {

    @InjectMocks
    private DomainProductService service;
    @Mock
    private  ProductRepository productRepository;
    @Mock
    private  EventService eventService;


    @Test
    void should_successfully_save_a_new_product() {

        // Arrange
        String sku = "00001";
        var productRequest = CreateProductRequest.builder()
                .skuId(sku)
                .name("Remera Blanca")
                .description("Remera blanca lisa moderna")
                .price(110.0)
                .category("CLOTHING")
                .stock(1500)
                .build();

        var newProduct = Product.createProduct(productRequest);

        when(productRepository.isSkuInUse(new Sku(sku))).thenReturn(false);
        doNothing().when(productRepository).save(newProduct);
        doNothing().when(eventService).newProductEvent(anyString(), any(UUID.class), any(NewProductEvent.class));

        // Act

        ProductDto productDto = service.createProduct(productRequest);
        var newProductEvent = ProductMapper.mapToProductEvent(productRequest);

        // Assert
        verify(productRepository).isSkuInUse(new Sku(sku));
        verify(productRepository).save(any(Product.class));
        verify(eventService).newProductEvent(anyString(), any(UUID.class), any(NewProductEvent.class));

        assertEquals(productDto.id(), newProduct.getId());
        assertEquals(productDto.skuId(), newProduct.getSkuId().skuId());
        assertEquals(productDto.name(), newProduct.getName().name());
        assertEquals(productDto.description(), newProduct.getDescription().description());
        assertEquals(productDto.price(), newProduct.getPrice().amount());
        assertEquals(productDto.category(), newProduct.getCategory());


        assertEquals(productDto.skuId(), newProductEvent.getSkuId());
        assertEquals(productDto.name(), newProductEvent.getName());
        assertEquals(productDto.description(), newProductEvent.getDescription());
        assertEquals(productDto.price(), newProductEvent.getPrice());
        assertEquals(productDto.category().name(), newProductEvent.getCategory());


    }

    @Test
    void should_successfully_delete_a_product() {

        // Arrange
        Long productId = 1L;
        Product existingProduct = createExistingProduct();

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        doNothing().when(productRepository).delete(existingProduct.getId());
        doNothing().when(eventService).deleteProductEvent(anyString(), any(UUID.class), any(DeleteProductEvent.class));

        // Act
        service.deleteProduct(existingProduct.getId());

        // Assert
        verify(productRepository).findById(existingProduct.getId());
        verify(productRepository).delete(existingProduct.getId());
        verify(eventService).deleteProductEvent(anyString(), any(UUID.class), any(DeleteProductEvent.class));
    }
    private Product createExistingProduct() {

        var productRequest = CreateProductRequest.builder()
                .skuId("1")
                .name("Remera Blanca")
                .description("Remera blanca lisa moderna")
                .price(110.0)
                .category("CLOTHING")
                .stock(1500)
                .build();

        Product newProduct = Product.createProduct(productRequest);

        ReflectionTestUtils.setField(newProduct,"id",1L);

        productRepository.save(newProduct);

        return newProduct;
    }

    @Test
    void updateProductName() {




    }
}