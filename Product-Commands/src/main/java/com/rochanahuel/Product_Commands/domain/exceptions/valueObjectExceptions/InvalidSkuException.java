package com.rochanahuel.Product_Commands.domain.exceptions.valueObjectExceptions;

public class InvalidSkuException extends RuntimeException{

    public InvalidSkuException(String message){
        super(message);
    }
}
