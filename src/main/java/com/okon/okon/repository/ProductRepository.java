package com.okon.okon.repository;

import com.okon.okon.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product insert(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    List<Product> findByName(String name);

    void deleteById(Long id);
}
