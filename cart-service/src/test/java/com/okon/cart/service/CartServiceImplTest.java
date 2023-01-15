package com.okon.cart.service;

import com.okon.api.dto.ProductDTO;
import com.okon.cart.model.Cart;
import com.okon.cart.model.CartItem;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CartServiceImplTest {
    @Autowired
    private CartServiceImpl cartServiceImpl;
    private static CartItem cartItemOne;
    private static BigDecimal totalPriceForOne;
    private static CartItem cartItemTwo;
    private static BigDecimal totalPriceForTwo;
    private static Long userId;
    private static ProductDTO productDTOOne;
    private static ProductDTO productDTOTwo;

    @BeforeEach
    void setUp() {
        userId = 1L;
        productDTOOne = ProductDTO.builder()
                .id(1L)
                .name("Test")
                .price(BigDecimal.valueOf(100))
                .image("test.jpg")
                .description("Test")
                .build();

        productDTOTwo = ProductDTO.builder()
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
        totalPriceForOne = BigDecimal.valueOf(200);

        cartItemTwo = CartItem.builder()
                .product(productDTOTwo)
                .quantity(4)
                .totalPrice(BigDecimal.valueOf(400))
                .build();
        totalPriceForTwo = BigDecimal.valueOf(400);
    }

    @AfterEach
    void clearCart() {
        cartServiceImpl.clearByUserId(userId);
        Assertions.assertTrue(cartServiceImpl.findByUserId(userId).isEmpty());
    }

    @Test
    void testInsertToCartOne() {
        Cart cartNull = cartServiceImpl.findByUserId(userId).orElse(null);
        Assertions.assertNull(cartNull);

        cartServiceImpl.insertToCart(cartItemOne, userId);
        Cart cart = cartServiceImpl.findByUserId(userId).orElse(null);

        Assertions.assertNotNull(cart);
        Assertions.assertEquals(1, cart.getProducts().size());
        Assertions.assertEquals(cartItemOne, cart.getProducts().iterator().next());
    }

    @Test
    void testInsertToCartTwo() {
        Cart cartNull = cartServiceImpl.findByUserId(userId).orElse(null);
        Assertions.assertNull(cartNull);

        cartServiceImpl.insertToCart(cartItemOne, userId);
        cartServiceImpl.insertToCart(cartItemTwo, userId);
        Cart cart = cartServiceImpl.findByUserId(userId).orElse(null);

        Assertions.assertNotNull(cart);
        Assertions.assertEquals(2, cart.getProducts().size());
        Assertions.assertTrue(cart.getProducts().contains(cartItemOne));
        Assertions.assertTrue(cart.getProducts().contains(cartItemTwo));
    }

    @Test
    void testTotalPriceIncrement() {
        Cart cartNull = cartServiceImpl.findByUserId(userId).orElse(null);
        Assertions.assertNull(cartNull);

        cartServiceImpl.insertToCart(cartItemOne, userId);
        Cart cart = cartServiceImpl.findByUserId(userId).orElse(null);
        Assertions.assertNotNull(cart);
        Assertions.assertEquals(totalPriceForOne, cart.getTotal());

        cartServiceImpl.insertToCart(cartItemTwo, userId);
        cart = cartServiceImpl.findByUserId(userId).orElse(null);
        Assertions.assertNotNull(cart);
        Assertions.assertEquals(totalPriceForOne.add(totalPriceForTwo), cart.getTotal());
    }

    @Test
    void testUpdateQuantity() {
        var newQuantity = 5;
        Cart cartNull = cartServiceImpl.findByUserId(userId).orElse(null);
        Assertions.assertNull(cartNull);

        cartServiceImpl.insertToCart(cartItemOne, userId);
        Cart cart = cartServiceImpl.findByUserId(userId).orElse(null);
        Assertions.assertNotNull(cart);
        Assertions.assertEquals(1, cart.getProducts().size());
        Assertions.assertEquals(cartItemOne, cart.getProducts().iterator().next());

        cartServiceImpl.updateQuantity(cartItemOne.getProduct().getId(), cartItemOne.getProduct().getId(), newQuantity);
        cart = cartServiceImpl.findByUserId(userId).orElse(null);
        Assertions.assertNotNull(cart);
        Assertions.assertEquals(1, cart.getProducts().size());
        Assertions.assertEquals(newQuantity, cart.getProducts().iterator().next().getQuantity());
        Assertions.assertEquals(BigDecimal.valueOf(newQuantity).multiply(productDTOOne.getPrice()), cart.getProducts().iterator().next().getTotalPrice());
    }

    @Test
    void testRemoveProductByUserId() {
        Cart cartNull = cartServiceImpl.findByUserId(userId).orElse(null);
        Assertions.assertNull(cartNull);

        cartServiceImpl.insertToCart(cartItemOne, userId);
        cartServiceImpl.insertToCart(cartItemTwo, userId);
        Cart cart = cartServiceImpl.findByUserId(userId).orElse(null);
        Assertions.assertNotNull(cart);
        Assertions.assertEquals(2, cart.getProducts().size());
        Assertions.assertTrue(cart.getProducts().contains(cartItemOne));
        Assertions.assertTrue(cart.getProducts().contains(cartItemTwo));

        cartServiceImpl.removeProductByUserId(cartItemOne.getProduct().getId(), userId);
        cart = cartServiceImpl.findByUserId(userId).orElse(null);
        Assertions.assertNotNull(cart);
        Assertions.assertEquals(1, cart.getProducts().size());
        Assertions.assertFalse(cart.getProducts().contains(cartItemOne));
        Assertions.assertTrue(cart.getProducts().contains(cartItemTwo));
    }
}
