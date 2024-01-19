package com.rochanahuel.Product_Commands.domain.valueObjects;

import com.rochanahuel.Product_Commands.domain.exceptions.valueObjectExceptions.InvalidProductNameException;
import jakarta.persistence.Embeddable;

@Embeddable
public record ProductName(String name) {
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 25;

    public ProductName{
       validateName(name);
    }

    public static void validateName(String name){
        if (name == null || name.length() < MIN_LENGTH || name.length() > MAX_LENGTH){
            throw new InvalidProductNameException(
                    "The product name must contain between " + MIN_LENGTH + " and " + MAX_LENGTH + " characters.");
        }
    }
}
