package com.rochanahuel.Product_Query.service;

import com.rochanahuel.Product_Query.dto.ProductDto;
import com.rochanahuel.Product_Query.kafka.dto.DeleteProductEvent;
import com.rochanahuel.Product_Query.kafka.dto.NewProductEvent;
import com.rochanahuel.Product_Query.kafka.dto.UpdateProductEvent;

import java.util.List;

public interface ProductService {
    ProductDto findProductById(String skuId);
    List<ProductDto> findAllProducts();
    void saveProduct(NewProductEvent newProduct);
    void deleteProduct(DeleteProductEvent deleteProduct);
    void updateProduct(UpdateProductEvent updateProduct);
}
