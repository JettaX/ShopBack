package com.okon.core.converter;

import com.okon.api.dto.BoughtProductDTO;
import com.okon.api.dto.OrderDTO;
import com.okon.core.model.BoughtProduct;
import com.okon.core.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class OrderConvertorTest {
    @Autowired
    private OrderConvertor orderConvertor;
    private static Order testOrder;
    private static OrderDTO testOrderDTO;

    @BeforeAll
    static void setUp() {
        BoughtProduct testBoughtProduct = new BoughtProduct(
                1L,
                1L,
                "Test",
                BigDecimal.valueOf(100),
                "Test",
                1,
                BigDecimal.valueOf(100)
        );
        BoughtProductDTO testBoughtProductDTO = new BoughtProductDTO(
                1L,
                1L,
                "Test",
                BigDecimal.valueOf(100),
                "Test",
                1,
                BigDecimal.valueOf(100)
        );
        testOrder = new Order(
                1L,
                "admin",
                List.of(testBoughtProduct),
                BigDecimal.valueOf(100)
        );
        testOrderDTO = new OrderDTO(
                1L,
                "admin",
                List.of(testBoughtProductDTO),
                BigDecimal.valueOf(100)
        );
    }

    @Test
    void convertToDTO() {
        OrderDTO orderDTO = orderConvertor.convertToDTO(testOrder);
        Assertions.assertEquals(testOrderDTO, orderDTO);
    }

    @Test
    void convertToEntity() {
        Order order = orderConvertor.convertToEntity(testOrderDTO);
        Assertions.assertEquals(testOrder, order);
    }
}