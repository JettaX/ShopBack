package com.okon.okon.service;

import com.okon.okon.dto.ProductDTO;
import com.okon.okon.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product insert(Product product);

    Product insertFromDTO(ProductDTO productDTO);

    Optional<Product> findById(int id);

    List<Product> findAll();

    List<Product> findByName(String name);

    void deleteById(String id);
}
