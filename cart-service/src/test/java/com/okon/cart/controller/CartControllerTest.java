package com.okon.cart.controller;

import com.okon.api.dto.CartDTO;
import com.okon.api.dto.CartItemDTO;
import com.okon.api.dto.ProductDTO;
import com.okon.cart.convertors.CartItemConvertor;
import com.okon.cart.model.Cart;
import com.okon.cart.model.CartItem;
import com.okon.cart.service.CartServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.Set;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CartControllerTest {
    @Autowired
    private CartServiceImpl cartServiceImpl;
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private CartItemConvertor cartItemConvertor;
    private static String userId;
    private static CartItem cartItemOne;
    private static BigDecimal totalPriceForOne;
    private static CartItem cartItemTwo;
    private static BigDecimal totalPriceForTwo;
    private static ProductDTO productDTOOne;
    private static ProductDTO productDTOTwo;

    @AfterEach
    void setUp() {
        cartServiceImpl.clear(userId);
    }

    @BeforeEach
    void setUpAll() {
        userId = "1";
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
        totalPriceForOne = cartItemOne.getTotalPrice();

        cartItemTwo = CartItem.builder()
                .product(productDTOTwo)
                .quantity(4)
                .totalPrice(BigDecimal.valueOf(400))
                .build();
        totalPriceForTwo = cartItemTwo.getTotalPrice();
    }

    @Test
    void testCartNotFound() {
        webTestClient.get().uri("/api/cart/1").exchange().expectStatus().isNotFound();
    }

    @Test
    void testGetCartByUserId() {
        cartServiceImpl.insertToCart(cartItemOne, userId);
        cartServiceImpl.insertToCart(cartItemTwo, userId);
        CartDTO cartDTO = webTestClient.get().uri("/api/cart/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(CartDTO.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(cartDTO);
        Assertions.assertEquals(userId, cartDTO.getUserId());
        Assertions.assertEquals(2, cartDTO.getProducts().size());
        Assertions.assertEquals(totalPriceForOne.add(totalPriceForTwo), cartDTO.getTotal());

        Set<CartItemDTO> cartItems = cartDTO.getProducts();
        Assertions.assertTrue(cartItems.contains(cartItemConvertor.convertToDTO(cartItemOne)));
        Assertions.assertTrue(cartItems.contains(cartItemConvertor.convertToDTO(cartItemTwo)));
    }

    @Test
    void testUpdateQuantityByProductIdAndUserId() {
        cartServiceImpl.insertToCart(cartItemOne, userId);
        var newQuantity = 5;
        CartItemDTO cartItemDTO = webTestClient.method(HttpMethod.PATCH).uri("/api/cart/1/1/" + newQuantity)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(CartItemDTO.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(cartItemDTO);
        Assertions.assertEquals(newQuantity, cartItemDTO.getQuantity());

        Cart cart = cartServiceImpl.find(userId);

        Assertions.assertNotNull(cart);
        Assertions.assertEquals(userId, cart.getUserId());
        Assertions.assertEquals(1, cart.getProducts().size());
        Assertions.assertEquals(productDTOOne.getPrice().multiply(BigDecimal.valueOf(newQuantity)), cart.getTotal());
        Assertions.assertEquals(cart.getProducts().iterator().next().getQuantity(), newQuantity);
    }

    @Test
    void testAddToCart() {
        webTestClient.post().uri("/api/cart/1")
                .bodyValue(cartItemOne)
                .exchange()
                .expectStatus()
                .isOk();

        Cart cart = cartServiceImpl.find(userId);

        Assertions.assertNotNull(cart);
        Assertions.assertEquals(userId, cart.getUserId());
        Assertions.assertEquals(1, cart.getProducts().size());
        Assertions.assertEquals(cartItemOne.getTotalPrice(), cart.getTotal());
        Assertions.assertEquals(cart.getProducts().iterator().next().getQuantity(), cartItemOne.getQuantity());
    }

    @Test
    void testClearCartByUserId() {
        cartServiceImpl.insertToCart(cartItemOne, userId);
        cartServiceImpl.insertToCart(cartItemTwo, userId);
        webTestClient.get().uri("/api/cart/clear/1")
                .exchange()
                .expectStatus()
                .isOk();

        Cart cart = cartServiceImpl.find(userId);
        Assertions.assertNull(cart);
    }

    @Test
    void testRemoveProductFromCartByUserId() {
        cartServiceImpl.insertToCart(cartItemOne, userId);
        cartServiceImpl.insertToCart(cartItemTwo, userId);
        webTestClient.delete().uri("/api/cart/1/1")
                .exchange()
                .expectStatus()
                .isOk();

        Cart cart = cartServiceImpl.find(userId);

        Assertions.assertNotNull(cart);
        Assertions.assertEquals(userId, cart.getUserId());
        Assertions.assertEquals(1, cart.getProducts().size());
        Assertions.assertEquals(totalPriceForTwo, cart.getTotal());
        Assertions.assertFalse(cart.getProducts().contains(cartItemOne));
    }
}