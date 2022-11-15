package com.okon.okon.repository;

import com.okon.okon.model.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private static Map<String, Product> products = new HashMap<>();

    @Override
    public Product insert(Product product) {
        return products.put(product.getId(), product);
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return products.values().stream().toList();
    }

    @Override
    public List<Product> findByName(String name) {
        return products.values()
                .stream()
                .filter(product -> product.getName().equals(name))
                .toList();
    }

    @Override
    public boolean deleteById(String id) {
        return !products.remove(id).getId().isEmpty();
    }
}
