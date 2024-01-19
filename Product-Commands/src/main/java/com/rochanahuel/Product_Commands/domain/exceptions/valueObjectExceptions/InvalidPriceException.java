package com.rochanahuel.Product_Commands.domain.exceptions.valueObjectExceptions;

public class InvalidPriceException extends RuntimeException{
    public InvalidPriceException(String message){
        super(message);
    }
}
