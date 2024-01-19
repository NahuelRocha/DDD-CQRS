package com.rochanahuel.Product_Commands.application.mapper;

import com.rochanahuel.Product_Commands.application.dto.business.CreateProductRequest;
import com.rochanahuel.Product_Commands.application.dto.kafka.NewProductEvent;
import com.rochanahuel.Product_Commands.application.dto.business.ProductDto;
import com.rochanahuel.Product_Commands.domain.entity.Product;


public class ProductMapper {
    public static ProductDto mapToProductDto(Product product){

        if (product == null) throw new NullPointerException("The product should not be null");

        return ProductDto.builder()
                .id(product.getId())
                .skuId(product.getSkuId().skuId())
                .name(product.getName().name())
                .description(product.getDescription().description())
                .price(product.getPrice().amount())
                .category(product.getCategory())
                .build();
    }

    public static NewProductEvent mapToProductEvent(CreateProductRequest request){

        if (request == null) throw new NullPointerException("The product request should not be null");

        return NewProductEvent.builder()
                .skuId(request.skuId())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .category(request.category())
                .stock(request.stock())
                .build();
    }
}
