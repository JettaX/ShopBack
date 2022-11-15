package com.okon.okon.config;

import com.okon.okon.service.ProductService;
import com.okon.okon.util.RandomProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoInit {

    @Autowired
    private void mongoInitialization(ProductService productService) {
        initProducts(productService);
    }

    private void initProducts(ProductService productService) {
        RandomProduct.getRandomProduct(50).forEach(productService::insert);
    }
}
