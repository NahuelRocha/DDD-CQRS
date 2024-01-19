package com.rochanahuel.Product_Commands.domain.exceptions.productException;

public class EmptyUpdatePropertiesException extends RuntimeException{
    public EmptyUpdatePropertiesException(String message){
        super(message);
    }
}
