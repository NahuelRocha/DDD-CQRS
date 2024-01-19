package com.rochanahuel.Product_Commands.application.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductEvent<T> {

    private String sku;
    private T updateValue;
}
