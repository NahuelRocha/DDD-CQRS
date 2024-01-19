package com.rochanahuel.Product_Commands.application.dto.business;

import java.util.Map;

public record UpdateProductRequest(Map<ProductPropsToUpdate, Object> updatesProperties) {
    public void validate() {
        updatesProperties.forEach(ProductPropsToUpdate::validate);
    }
}
