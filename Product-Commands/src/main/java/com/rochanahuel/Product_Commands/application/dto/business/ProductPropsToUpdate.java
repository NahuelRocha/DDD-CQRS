package com.rochanahuel.Product_Commands.application.dto.business;

import com.rochanahuel.Product_Commands.domain.entity.Product;
import com.rochanahuel.Product_Commands.domain.valueObjects.Category;
import com.rochanahuel.Product_Commands.domain.valueObjects.Price;
import com.rochanahuel.Product_Commands.domain.valueObjects.ProductDescription;
import com.rochanahuel.Product_Commands.domain.valueObjects.ProductName;

public enum ProductPropsToUpdate {
    NAME {
        @Override
        public void validate(Object value) {
            ProductName.validateName((String) value);
        }

        @Override
        public void updateProperty(Product product, Object value) {
            product.updateName((String) value);
        }
    },
    DESCRIPTION {
        @Override
        public void validate(Object value) {
            ProductDescription.validateDescription((String) value);
        }

        @Override
        public void updateProperty(Product product, Object value) {
            product.updateDescription((String) value);
        }
    },
    PRICE {
        @Override
        public void validate(Object value) {
            Price.validateAmount((Double) value);
        }

        @Override
        public void updateProperty(Product product, Object value) {
            product.updatePrice((Double) value);
        }
    },
    CATEGORY {
        @Override
        public void validate(Object value) {
            Category.isValidCategory((String) value);
        }

        @Override
        public void updateProperty(Product product, Object value) {
            product.updateCategory((String) value);
        }
    };

    public abstract void validate(Object value);

    public abstract void updateProperty(Product product, Object value);


}
