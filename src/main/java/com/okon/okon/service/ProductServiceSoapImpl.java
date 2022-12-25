package com.okon.okon.service;

import com.okon.okon.model.Product;
import com.okon.okon.repository.ProductRepository;
import com.okon.okon.soap.product.ProductSOAP;
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
