package com.okon.cart.convertors;

import com.okon.api.dto.CartDTO;
import com.okon.api.dto.ProductDTO;
import com.okon.cart.model.Cart;
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
class CartConvertorTest {
    @Autowired
    private CartConvertor cartConvertor;
    private static Cart cart;

    @BeforeAll
    static void setUpAll() {
        ProductDTO productDTOOne = ProductDTO.builder()
                .id(1L)
                .name("Test")
                .price(BigDecimal.valueOf(100))
                .image("test.jpg")
                .description("Test")
                .build();

        CartItem cartItemOne = CartItem.builder()
                .product(productDTOOne)
                .quantity(2)
                .totalPrice(BigDecimal.valueOf(200))
                .build();

        cart = Cart.builder()
                .userId(1L)
                .total(BigDecimal.valueOf(200))
                .products(Set.of(cartItemOne))
                .build();
    }


    @Test
    void convertToDTO() {
        CartDTO cartDTO = cartConvertor.convertToDTO(cart);
        Assertions.assertEquals(cartDTO.getUserId(), cart.getUserId());
        Assertions.assertEquals(cartDTO.getTotal(), cart.getTotal());
        Assertions.assertEquals(cartDTO.getProducts().size(), cart.getProducts().size());
        Assertions.assertEquals(cartDTO.getProducts().iterator().next().getQuantity(), cart.getProducts().iterator().next().getQuantity());
        Assertions.assertEquals(cartDTO.getProducts().iterator().next().getTotalPrice(), cart.getProducts().iterator().next().getTotalPrice());
        Assertions.assertEquals(cartDTO.getProducts().iterator().next().getProduct().getId(), cart.getProducts().iterator().next().getProduct().getId());
        Assertions.assertEquals(cartDTO.getProducts().iterator().next().getProduct().getName(), cart.getProducts().iterator().next().getProduct().getName());
        Assertions.assertEquals(cartDTO.getProducts().iterator().next().getProduct().getPrice(), cart.getProducts().iterator().next().getProduct().getPrice());
        Assertions.assertEquals(cartDTO.getProducts().iterator().next().getProduct().getImage(), cart.getProducts().iterator().next().getProduct().getImage());
        Assertions.assertEquals(cartDTO.getProducts().iterator().next().getProduct().getDescription(), cart.getProducts().iterator().next().getProduct().getDescription());
    }
}