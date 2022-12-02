package com.okon.okon.service;

import com.okon.okon.dto.ProductDTO;
import com.okon.okon.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product insert(Product product);

    Page<Product> find(Integer minPrice, Integer maxPrice, Integer page, Integer limit);

    Product insertFromDTO(ProductDTO productDTO);

    Optional<Product> findById(Long id);

    List<Product> findByName(String name);

    void deleteById(Long id);
}
