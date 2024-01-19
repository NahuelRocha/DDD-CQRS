package com.rochanahuel.Product_Commands.domain.exceptions.productException;

public class InvalidStockException extends RuntimeException{
    public InvalidStockException(String message){
        super(message);
    }
}
