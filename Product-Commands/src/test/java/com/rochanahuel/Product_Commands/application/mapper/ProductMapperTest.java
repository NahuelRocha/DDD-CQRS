package com.rochanahuel.Product_Commands.application.mapper;

import com.rochanahuel.Product_Commands.application.dto.business.CreateProductRequest;
import com.rochanahuel.Product_Commands.application.dto.business.ProductDto;
import com.rochanahuel.Product_Commands.application.dto.kafka.NewProductEvent;
import com.rochanahuel.Product_Commands.domain.entity.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    public void shouldMapProductToProductDto(){

        var product = Product.createProduct(
                new CreateProductRequest(
                        "00001",
                        "Remera",
                        "Remera color blanca",
                        100.0,
                        "CLOTHING",
                        100
                )
        );

        ProductDto productDto = ProductMapper.mapToProductDto(product);

        assertEquals(product.getId(), productDto.id());
        assertEquals(product.getSkuId().skuId(), productDto.skuId());
        assertEquals(product.getName().name(), productDto.name());
        assertEquals(product.getDescription().description(), productDto.description());
        assertEquals(product.getPrice().amount(), productDto.price());
        assertEquals(product.getCategory(), productDto.category());
    }

    @Test
    public void should_throw_null_pointer_exception_when_product_is_null(){

        var ex = assertThrows(NullPointerException.class, () -> ProductMapper.mapToProductDto(null));

        assertEquals("The product should not be null", ex.getMessage());
    }

    @Test
    public void shouldMapToProductEvent(){
        var productEventRequest = CreateProductRequest.builder()
                .skuId("00001")
                .name("Remera Blanca")
                .description("Remera de color blanca lisa")
                .price(100.0)
                .category("CLOTHING")
                .stock(1000)
                .build();

        NewProductEvent productEvent = ProductMapper.mapToProductEvent(productEventRequest);

        assertEquals(productEventRequest.name(), productEvent.getName());
        assertEquals(productEventRequest.skuId(), productEvent.getSkuId());
        assertEquals(productEventRequest.description(), productEvent.getDescription());
        assertEquals(productEventRequest.price(), productEvent.getPrice());
        assertEquals(productEventRequest.category(), productEvent.getCategory());
        assertEquals(productEventRequest.stock(), productEvent.getStock());
    }

    @Test
    public void should_throw_null_pointer_exception_when_create_product_request_is_null(){

        var ex = assertThrows(NullPointerException.class, () -> ProductMapper.mapToProductEvent(null));

        assertEquals("The product request should not be null", ex.getMessage());
    }
}