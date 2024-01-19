package com.rochanahuel.Product_Commands.infraestructure.persistence;

import com.rochanahuel.Product_Commands.domain.entity.Product;
import com.rochanahuel.Product_Commands.domain.repository.ProductRepository;
import com.rochanahuel.Product_Commands.domain.valueObjects.Sku;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DomainProductRepository implements ProductRepository {

    private final MySqlProductRepository mySqlProductRepository;
    @Override
    public void save(Product product) {
        mySqlProductRepository.save(product);
    }

    @Override
    public void delete(Long productId) {mySqlProductRepository.deleteById(productId);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return mySqlProductRepository.findById(id);
    }

    @Override
    public boolean isSkuInUse(Sku skuId) {
        return mySqlProductRepository.existsBySkuId(skuId);
    }
}
