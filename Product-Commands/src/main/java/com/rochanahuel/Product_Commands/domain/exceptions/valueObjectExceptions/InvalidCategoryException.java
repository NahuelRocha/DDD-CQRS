package com.rochanahuel.Product_Commands.domain.exceptions.valueObjectExceptions;

public class InvalidCategoryException extends RuntimeException{
    public InvalidCategoryException(String message){
        super(message);
    }
}
