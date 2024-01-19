package com.rochanahuel.Product_Commands.domain.valueObjects;

import com.rochanahuel.Product_Commands.domain.exceptions.valueObjectExceptions.InvalidSkuException;
import jakarta.persistence.Embeddable;

@Embeddable
public record Sku(String skuId) {
    private static final int VALID_MIN_LENGTH = 1;
    private static final int VALID_MAX_LENGTH = 5;
    public Sku{
        validateSku(skuId);
    }

    private static void validateSku(String skuId){
        if (skuId == null || skuId.length() < VALID_MIN_LENGTH || skuId.length() > VALID_MAX_LENGTH){
            throw new InvalidSkuException("The SKU must contain a min of "+ VALID_MIN_LENGTH + " and a max of " + VALID_MAX_LENGTH);
        }
    }
}
