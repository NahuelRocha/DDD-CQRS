package com.rochanahuel.Product_Commands.application.dto.business;
import lombok.Builder;

@Builder
public record CreateProductRequest(String skuId,
                                   String name,
                                   String description,
                                   Double price,
                                   String category,
                                   Integer stock) {
}
