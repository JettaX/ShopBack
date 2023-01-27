package com.okon.core.service;

import com.okon.api.dto.*;
import com.okon.core.integration.AuthServiceIntegration;
import com.okon.core.integration.CartServiceIntegration;
import com.okon.core.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class OrdersServiceImplTest {
    @Autowired
    private OrdersServiceImpl ordersService;
    @MockBean
    private AuthServiceIntegration authService;
    @MockBean
    private CartServiceIntegration cartService;
    private static UserDTO testUser;
    private static CartDTO cartDTO;
    private static Long userId;
    private static Long productId;
    private static String username;
    private static CartItemDTO cartItemOne;

    @BeforeAll
    static void setUp() {
        userId = 666L;
        username = "test";
        productId = 666L;
        testUser = new UserDTO(
                userId,
                username,
                username,
                List.of(new RoleDTO(1L, "ADMIN"))
        );
        ProductDTO productDTOOne = ProductDTO.builder()
                .id(productId)
                .name("Test")
                .price(BigDecimal.valueOf(100))
                .image("test.jpg")
                .description("Test")
                .build();


        cartItemOne = CartItemDTO.builder()
                .product(productDTOOne)
                .quantity(2)
                .totalPrice(BigDecimal.valueOf(200))
                .build();

        cartDTO = new CartDTO(
                userId.toString(),
                Set.of(cartItemOne),
                cartItemOne.getTotalPrice()
        );
    }

    @Test
    @Transactional
    void createOrder() {
        Mockito.when(authService.findByUsername(username, "token")).thenReturn(Optional.ofNullable(testUser));
        Mockito.when(cartService.findByUserId(userId)).thenReturn(Optional.ofNullable(cartDTO));
        ordersService.createOrder(username, "token");

        List<Order> order = ordersService.findUserOrders(username);

        Assertions.assertEquals(1, order.size());
        Assertions.assertEquals(0, cartDTO.getTotal().compareTo(order.get(0).getTotalPrice()));
        Assertions.assertEquals(1, cartDTO.getProducts().size(), order.get(0).getProducts().size());
        Assertions.assertEquals(testUser.getName(), order.get(0).getUsername());
        Assertions.assertEquals(order.get(0).getProducts().get(0).getName(), cartItemOne.getProduct().getName());
        Assertions.assertEquals(order.get(0).getProducts().get(0).getPrice(), cartItemOne.getProduct().getPrice());
        Assertions.assertEquals(order.get(0).getProducts().get(0).getQuantity(), cartItemOne.getQuantity());
        Assertions.assertEquals(order.get(0).getProducts().get(0).getImage(), cartItemOne.getProduct().getImage());
    }

    @Test
    void getCountOfBought() {
        Mockito.when(authService.findByUsername(username, "token")).thenReturn(Optional.ofNullable(testUser));
        Mockito.when(cartService.findByUserId(userId)).thenReturn(Optional.ofNullable(cartDTO));

        ordersService.createOrder(username, "token");

        Assertions.assertEquals(2, ordersService.getCountOfBought(productId));
    }

}