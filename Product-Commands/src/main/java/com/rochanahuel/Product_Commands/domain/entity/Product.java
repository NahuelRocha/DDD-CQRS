package com.rochanahuel.Product_Commands.domain.entity;

import com.rochanahuel.Product_Commands.application.dto.business.CreateProductRequest;
import com.rochanahuel.Product_Commands.domain.exceptions.productException.TheDescriptionIsEqualException;
import com.rochanahuel.Product_Commands.domain.exceptions.productException.TheNameIsEqualException;
import com.rochanahuel.Product_Commands.domain.exceptions.valueObjectExceptions.InvalidCategoryException;
import com.rochanahuel.Product_Commands.domain.exceptions.valueObjectExceptions.InvalidPriceException;
import com.rochanahuel.Product_Commands.domain.valueObjects.*;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Entity(name = "products")
@ToString
@EqualsAndHashCode
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sku_id")
    @Embedded
    private Sku skuId;
    @Column(name = "name")
    @Embedded
    private ProductName name;
    @Embedded
    private ProductDescription description;
    @Column(name = "price")
    @Embedded
    private Price price;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Product(){}

    public Product(String skuId, String productName, String description, Double price, Category category) {
        this.skuId = new Sku(skuId);
        this.name = new ProductName(productName);
        this.description = new ProductDescription(description);
        this.price = new Price(price);
        this.category = category;
    }

    public static Product createProduct(CreateProductRequest createProductRequest) {

        Category category = Category.isValidCategory(createProductRequest.category());

        return new Product(
                createProductRequest.skuId(),
                createProductRequest.name(),
                createProductRequest.description(),
                createProductRequest.price(),
                category
        );
    }

    public void updateName(String name){
        if (this.name.name().equals(name)) throw new TheNameIsEqualException(
                "The new name is the same as the current one.");
        this.name = new ProductName(name);
    }

    public void updateDescription(String description){
        if (this.description.description().equals(description)) throw new TheDescriptionIsEqualException(
                "The new description is the same as the current one.");
        this.description = new ProductDescription(description);
    }

    public void updatePrice(Double price){
        if (this.price.amount().equals(price)) throw new InvalidPriceException(
                "The new price is the same as the current one.");
        this.price = new Price(price);
    }

    public void updateCategory(String category){
        if (this.category.name().equals(category)) throw new InvalidCategoryException(
                "The new category is the same as the current one.");
        this.category = Category.isValidCategory(category);
    }

}
