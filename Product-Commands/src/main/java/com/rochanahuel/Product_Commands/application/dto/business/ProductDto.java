package com.rochanahuel.Product_Commands.application.dto.business;

import com.rochanahuel.Product_Commands.domain.valueObjects.Category;
import lombok.Builder;

@Builder
public record ProductDto(Long id,
                         String skuId,
                         String name,
                         String description,
                         Double price,
                         Category category) {

}
