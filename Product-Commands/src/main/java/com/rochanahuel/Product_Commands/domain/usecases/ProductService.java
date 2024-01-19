package com.rochanahuel.Product_Commands.domain.usecases;

import com.rochanahuel.Product_Commands.application.dto.business.CreateProductRequest;
import com.rochanahuel.Product_Commands.application.dto.business.ProductDto;
import com.rochanahuel.Product_Commands.application.dto.business.UpdateProductRequest;

public interface ProductService {

    ProductDto createProduct(CreateProductRequest createProductRequest);

    void deleteProduct(Long productId);

    void updateProduct(Long productId, UpdateProductRequest name);

}
