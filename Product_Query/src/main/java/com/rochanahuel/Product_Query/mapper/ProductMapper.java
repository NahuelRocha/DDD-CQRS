package com.rochanahuel.Product_Query.mapper;

import com.rochanahuel.Product_Query.dto.ProductDto;
import com.rochanahuel.Product_Query.kafka.dto.NewProductEvent;
import com.rochanahuel.Product_Query.model.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class ProductMapper {

    public Product mapNewEventToProduct(NewProductEvent newProductEvent){

        return Product.builder()
                .skuId(newProductEvent.getSkuId())
                .name(newProductEvent.getName())
                .description(newProductEvent.getDescription())
                .price(newProductEvent.getPrice())
                .category(newProductEvent.getCategory())
                .build();
    }

    public ProductDto mapProductToProductDto(Product product){

        return ProductDto.builder()
                .skuId(product.getSkuId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }
}
