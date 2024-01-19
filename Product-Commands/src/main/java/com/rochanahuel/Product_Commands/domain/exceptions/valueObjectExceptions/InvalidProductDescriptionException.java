package com.rochanahuel.Product_Commands.domain.exceptions.valueObjectExceptions;

public class InvalidProductDescriptionException extends RuntimeException{
    public InvalidProductDescriptionException(String message){
        super(message);
    }
}
