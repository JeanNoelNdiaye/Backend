package com.galimagroup.back.repository;

import com.galimagroup.back.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}

