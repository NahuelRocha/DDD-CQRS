package com.rochanahuel.Product_Query.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String skuId;
    private String name;
    private String description;
    private Double price;
    private String category;

}
