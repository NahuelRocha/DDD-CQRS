package com.rochanahuel.Product_Commands.domain.repository;

import com.rochanahuel.Product_Commands.domain.entity.Product;
import com.rochanahuel.Product_Commands.domain.valueObjects.Sku;

import java.util.Optional;

public interface ProductRepository {
    void save(Product product);
    void delete(Long productId);
    Optional<Product> findById(Long id);
    boolean isSkuInUse(Sku skuId);
}
