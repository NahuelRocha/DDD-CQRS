package com.rochanahuel.Product_Commands.domain.exceptions.productException;

public class TheNameIsEqualException extends RuntimeException{
    public TheNameIsEqualException(String message){
        super(message);
    }
}
