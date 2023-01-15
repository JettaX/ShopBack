package com.okon.cart.convertors;

import com.okon.api.dto.CartItemDTO;
import com.okon.api.dto.ProductDTO;
import com.okon.cart.model.CartItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Set;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CartItemConvertorTest {
    @Autowired
    private CartItemConvertor itemConvertor;

    private static CartItem cartItemOne;
    private static CartItem cartItemTwo;

    @BeforeAll
    static void setUpAll() {
        ProductDTO productDTOOne = ProductDTO.builder()
                .id(1L)
                .name("Test")
                .price(BigDecimal.valueOf(100))
                .image("test.jpg")
                .description("Test")
                .build();

        ProductDTO productDTOTwo = ProductDTO.builder()
                .id(2L)
                .name("Test")
                .price(BigDecimal.valueOf(100))
                .image("test.jpg")
                .description("Test")
                .build();

        cartItemOne = CartItem.builder()
                .product(productDTOOne)
                .quantity(2)
                .totalPrice(BigDecimal.valueOf(200))
                .build();

        cartItemTwo = CartItem.builder()
                .product(productDTOTwo)
                .quantity(4)
                .totalPrice(BigDecimal.valueOf(400))
                .build();
    }

    @Test
    void convertToDTO() {
        CartItemDTO cartItemDTO = itemConvertor.convertToDTO(cartItemOne);
        Assertions.assertNotNull(cartItemDTO);
        Assertions.assertEquals(cartItemOne.getProduct(), cartItemDTO.getProduct());
        Assertions.assertEquals(cartItemOne.getQuantity(), cartItemDTO.getQuantity());
        Assertions.assertEquals(cartItemOne.getTotalPrice(), cartItemDTO.getTotalPrice());
    }

    @Test
    void testConvertToDTO() {
        Set<CartItemDTO> cartItemDTO = itemConvertor.convertToDTO(Set.of(cartItemOne, cartItemTwo));
        Assertions.assertNotNull(cartItemDTO);
        Assertions.assertEquals(2, cartItemDTO.size());
        Assertions.assertTrue(cartItemDTO.contains(itemConvertor.convertToDTO(cartItemOne)));
        Assertions.assertTrue(cartItemDTO.contains(itemConvertor.convertToDTO(cartItemTwo)));
    }
}