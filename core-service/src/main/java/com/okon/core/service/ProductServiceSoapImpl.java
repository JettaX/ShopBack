package com.okon.core.service;


import com.okon.core.soap.product.ProductSOAP;
import com.okon.core.model.Product;
import com.okon.core.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProductServiceSoapImpl implements ProductServiceSoap {
    private final ProductRepository productRepository;

    @Override
    public List<ProductSOAP> getProducts() {
        return productRepository.findAll().stream().map(functionProductToProductSOAP).toList();
    }

    private static final Function<Product, ProductSOAP> functionProductToProductSOAP = pr ->  {
        ProductSOAP product = new ProductSOAP();
        product.setId(pr.getId());
        product.setName(pr.getName());
        product.setDescription(pr.getDescription());
        product.setPrice(pr.getPrice());
        product.setImage(pr.getImage());
        return product;
    };
}
