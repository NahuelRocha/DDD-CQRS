package com.rochanahuel.Product_Query.dto;

import lombok.Builder;

@Builder
public record ProductDto(
        String skuId,
        String name,
        String description,
        Double price,
        String category
) {
}
