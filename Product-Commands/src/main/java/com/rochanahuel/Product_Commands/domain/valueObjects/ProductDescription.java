package com.rochanahuel.Product_Commands.domain.valueObjects;

import com.rochanahuel.Product_Commands.domain.exceptions.valueObjectExceptions.InvalidProductDescriptionException;
import jakarta.persistence.Embeddable;

@Embeddable
public record ProductDescription(String description) {
    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 245;
    public ProductDescription{
        validateDescription(description);
    }
    public static void validateDescription(String description){
        if (description == null || description.length() < MIN_LENGTH || description.length() > MAX_LENGTH){
            throw new InvalidProductDescriptionException
                    ("The product description must contain between " + MIN_LENGTH + " and " + MAX_LENGTH + " characters.");
        }
    }
}
