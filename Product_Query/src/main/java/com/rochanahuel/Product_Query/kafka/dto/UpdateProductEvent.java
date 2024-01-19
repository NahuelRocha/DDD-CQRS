package com.rochanahuel.Product_Query.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductEvent {

    private String sku;
    private Map<String,Object> updateValue;
}
