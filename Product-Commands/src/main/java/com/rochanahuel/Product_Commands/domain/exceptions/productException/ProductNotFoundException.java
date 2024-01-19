package com.rochanahuel.Product_Commands.domain.exceptions.productException;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message){
        super(message);
    }
}
