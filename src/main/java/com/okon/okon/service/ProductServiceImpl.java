package com.okon.okon.service;

import com.okon.okon.dto.ProductDTO;
import com.okon.okon.model.Product;
import com.okon.okon.repository.ProductRepository;
import com.okon.okon.repository.specifications.ProductsSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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
        return productRepository.save(product);
    }

    @Override
    public Page<Product> find(Integer minPrice, Integer maxPrice, Integer page, Integer limit) {
        Specification<Product> specification = Specification.where(null);
        if (minPrice != null) {
            specification = specification.and(ProductsSpecifications.priceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ProductsSpecifications.priceLessThanOrEqualTo(maxPrice));
        }
        return productRepository.findAll(specification, PageRequest.of(page, limit));
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
