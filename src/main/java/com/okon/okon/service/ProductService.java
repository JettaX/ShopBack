package com.okon.okon.service;

import com.okon.okon.dto.ProductDTO;
import com.okon.okon.model.Filter;
import com.okon.okon.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product insert(Product product);

    Product insertFromDTO(ProductDTO productDTO);

    Optional<Product> findById(Long id);

    List<Product> findAllByFilter(Filter filter, int offset, int limit);

    List<Product> findByName(String name);

    void deleteById(Long id);

    Long getCountProducts(Filter filter);
}
