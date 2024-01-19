package com.rochanahuel.Product_Commands.domain.valueObjects;

import com.rochanahuel.Product_Commands.domain.exceptions.valueObjectExceptions.InvalidCategoryException;

import java.util.stream.Stream;

public enum Category {
    SPORTS,
    ELECTRONICS,
    HOME,
    CLOTHING,
    OTHER;

    public static Category isValidCategory(String value) {
        var isValid = value != null && Stream.of(Category.values())
                .map(Enum::name)
                .anyMatch(validCategory -> validCategory.equals(value));
        if (!isValid){
            throw new InvalidCategoryException("Invalid category name");
        }

        return Category.valueOf(value);
    }

}
