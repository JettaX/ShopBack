package com.okon.core.converter;

import com.okon.api.dto.ProductDTO;
import com.okon.core.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ProductConvertorTest {
    @Autowired
    private ProductConvertor productConvertor;
    private static Product testProduct;
    private static ProductDTO testProductDTO;

    @BeforeAll
    static void setUp() {
        testProductDTO = new ProductDTO(
                1L,
                "Test",
                "Test",
                BigDecimal.valueOf(100),
                "Test"
        );
        testProduct = new Product(
                1L,
                "Test",
                "Test",
                BigDecimal.valueOf(100),
                "Test"
        );
    }

    @Test
    void convertToDTO() {
        ProductDTO productDTO = productConvertor.convertToDTO(testProduct);
        Assertions.assertEquals(testProductDTO, productDTO);
    }

    @Test
    void convertToEntity() {
        Product product = productConvertor.convertToEntity(testProductDTO);
        Assertions.assertEquals(testProduct, product);
    }

}