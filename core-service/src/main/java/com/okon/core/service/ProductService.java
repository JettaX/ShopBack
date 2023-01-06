package com.okon.core.service;


import com.okon.api.dto.ProductDTO;
import com.okon.core.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product insert(Product product);

    Page<Product> find(Integer minPrice, Integer maxPrice, Integer page, Integer limit, String name);

    Product insertFromDTO(ProductDTO productDTO);

    Optional<Product> findById(Long id);

    List<Product> findByName(String name);

    void deleteById(Long id);
}
