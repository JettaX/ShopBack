package com.okon.okon.service;

import com.okon.okon.dto.ProductDTO;
import com.okon.okon.model.Product;
import com.okon.okon.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product insert(Product product) {
        log.debug("inserting product {}", product);
        return productRepository.insert(product);
    }

    @Override
    public Product insertFromDTO(ProductDTO productDTO) {
        return insert(Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .image(productDTO.getImage())
                .build());
    }

    @Override
    public Optional<Product> findById(Long id) {
        log.debug("findById {} ", id);
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        log.debug("findAll");
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByName(String name) {
        log.debug("findAllByName {} ", name);
        return productRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("deleteById {} ", id);
        productRepository.deleteById(id);
    }
}