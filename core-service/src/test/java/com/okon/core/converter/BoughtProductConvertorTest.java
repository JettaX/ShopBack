package com.okon.core.converter;

import com.okon.api.dto.BoughtProductDTO;
import com.okon.core.model.BoughtProduct;
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
class BoughtProductConvertorTest {
    @Autowired
    private BoughtProductConvertor boughtProductConvertor;
    private static BoughtProduct testBoughtProduct;
    private static BoughtProductDTO testBoughtProductDTO;

    @BeforeAll
    static void setUp() {
        testBoughtProductDTO = new BoughtProductDTO(
                1L,
                1L,
                "Test",
                BigDecimal.valueOf(100),
                "Test",
                1,
                BigDecimal.valueOf(100)
        );
        testBoughtProduct = new BoughtProduct(
                1L,
                1L,
                "Test",
                BigDecimal.valueOf(100),
                "Test",
                1,
                BigDecimal.valueOf(100)
        );
    }


    @Test
    void convertToDTO() {
        BoughtProductDTO boughtProductDTO = boughtProductConvertor.convertToDTO(testBoughtProduct);
        Assertions.assertEquals(testBoughtProductDTO, boughtProductDTO);
    }

    @Test
    void convertToEntity() {
        BoughtProduct boughtProduct = boughtProductConvertor.convertToEntity(testBoughtProductDTO);
        Assertions.assertEquals(testBoughtProduct, boughtProduct);
    }
}