package com.rochanahuel.Product_Commands.domain.exceptions.productException;

public class SkuIsInUseException extends RuntimeException{
    public SkuIsInUseException(String message){
        super(message);
    }
}
