package com.okon.core.controller;


import com.okon.api.dto.ProductDTO;
import com.okon.core.model.Product;
import com.okon.core.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<Product> getProducts(
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) String name,
            @RequestParam Integer page,
            @RequestParam Integer limit) {
        log.info("getProducts with maxPrice {}, minPrice {}, name {}, page {}, limit {}", maxPrice, minPrice, name, page, limit);
        return productService.find(minPrice, maxPrice, page, limit, name);
    }

    @GetMapping("/findByName/{productName}")
    public List<Product> getProductsByName(@PathVariable String productName) {
        log.info("getProductsByName: {}", productName);
        return productService.findByName(productName);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductsById(@PathVariable Long id) {
        log.info("getProductsById: {}", id);
        return productService.findById(id);
    }

    @PostMapping
    public Product saveProduct(@RequestBody ProductDTO product) {
        log.info("Adding product: {}", product);
        return productService.insertFromDTO(product);
    }

    @PostMapping("/update/{id}")
    public Product updateProduct(@RequestBody ProductDTO product, @PathVariable Long id) {
        product.setId(id);
        log.info("Update product: {}", product);
        return productService.insertFromDTO(product);
    }
    
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        log.info("Deleting product: {}", id);
        productService.deleteById(id);
    }
}
