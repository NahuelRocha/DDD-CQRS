package com.rochanahuel.Product_Commands.domain.valueObjects;

import com.rochanahuel.Product_Commands.domain.exceptions.valueObjectExceptions.InvalidPriceException;
import jakarta.persistence.Embeddable;

@Embeddable
public record Price(Double amount) {

    private static final Double MIN_VALID_AMOUNT = 0.0;

    public Price {
        validateAmount(amount);
    }

    public static void validateAmount(Double amount) {
        if (amount == null || amount < MIN_VALID_AMOUNT) {
            throw new InvalidPriceException("The price must be greater than " + MIN_VALID_AMOUNT);
        }
    }

}
