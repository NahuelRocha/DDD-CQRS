package com.rochanahuel.Product_Commands.application.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewProductEvent {

    private String skuId;
    private String name;
    private String description;
    private Double price;
    private String category;
    private Integer stock;
}
