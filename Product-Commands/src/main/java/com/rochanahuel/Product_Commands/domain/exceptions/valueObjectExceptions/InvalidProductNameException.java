package com.rochanahuel.Product_Commands.domain.exceptions.valueObjectExceptions;

public class InvalidProductNameException extends RuntimeException{
    public InvalidProductNameException(String message){
        super(message);
    }
}
