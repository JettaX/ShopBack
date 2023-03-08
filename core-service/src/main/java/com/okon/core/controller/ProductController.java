package com.okon.core.controller;


import com.okon.api.dto.ProductDTO;
import com.okon.core.anotations.Authorities;
import com.okon.core.anotations.hasAuthority;
import com.okon.core.converter.ProductConvertor;
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
    private final ProductConvertor productConvertor;

    @GetMapping
    public Page<ProductDTO> getProducts(
            @RequestHeader String roles,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) String name,
            @RequestParam Integer page,
            @RequestParam Integer limit) {
        log.info("getProducts with maxPrice {}, minPrice {}, name {}, page {}, limit {}", maxPrice, minPrice, name, page, limit);
        return productConvertor.convertToDTO(productService.find(minPrice, maxPrice, page, limit, name));
    }

    @GetMapping("/findByName/{productName}")
    public List<ProductDTO> getProductsByName(@PathVariable String productName) {
        log.info("getProductsByName: {}", productName);
        return productConvertor.convertToDTO(productService.findByName(productName));
    }

    @GetMapping("/{id}")
    public Optional<ProductDTO> getProductsById(@PathVariable Long id) {
        log.info("getProductsById: {}", id);
        return productService.findById(id).map(productConvertor::convertToDTO);
    }

    @hasAuthority(value = Authorities.ADMIN)
    @PostMapping
    public ProductDTO saveProduct(@RequestBody ProductDTO product) {
        log.info("Adding product: {}", product);
        return productConvertor.convertToDTO(productService.insertFromDTO(product));
    }

    @hasAuthority(value = Authorities.ADMIN)
    @PostMapping("/update/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO product, @PathVariable Long id) {
        product.setId(id);
        log.info("Update product: {}", product);
        return productConvertor.convertToDTO(productService.insertFromDTO(product));
    }

    @hasAuthority(value = Authorities.ADMIN)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        log.info("Deleting product: {}", id);
        productService.deleteById(id);
    }
}
