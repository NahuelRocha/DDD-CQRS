package com.rochanahuel.Product_Commands.infraestructure.persistence;

import com.rochanahuel.Product_Commands.domain.entity.Product;
import com.rochanahuel.Product_Commands.domain.valueObjects.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySqlProductRepository extends JpaRepository<Product,Long> {
    boolean existsBySkuId(Sku skuId);
}
