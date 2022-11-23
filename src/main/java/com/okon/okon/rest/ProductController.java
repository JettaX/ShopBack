package com.okon.okon.rest;

import com.okon.okon.dto.ProductDTO;
import com.okon.okon.model.Product;
import com.okon.okon.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/product")
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/getProducts")
    public List<Product> getProducts() {
        log.info("getProducts");
        return productService.findAll();
    }

    @GetMapping("/getProductsByName/{productName}")
    public List<Product> getProductsByName(@PathVariable String productName) {
        log.info("getProductsByName: {}", productName);
        return productService.findByName(productName);
    }

    @GetMapping("/getProductsById/{id}")
    public Optional<Product> getProductsById(@PathVariable Long id) {
        log.info("getProductsById: {}", id);
        return productService.findById(id);
    }

    @PostMapping("/addProduct")
    public Product saveProduct(@RequestBody ProductDTO product) {
        log.info("Adding product: {}", product);
        return productService.insertFromDTO(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id) {
        log.info("Deleting product: {}", id);
        productService.deleteById(id);
    }
}
