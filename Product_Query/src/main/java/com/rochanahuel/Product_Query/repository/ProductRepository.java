package com.rochanahuel.Product_Query.repository;

import com.rochanahuel.Product_Query.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
