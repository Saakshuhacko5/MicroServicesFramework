package com.backendServices.productService.repository;

import com.backendServices.productService.model.Product;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;


@EnableAutoConfiguration

public interface ProductRepository extends MongoRepository<Product, String> {
}
