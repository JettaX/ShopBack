package com.okon.okon.config;

import com.okon.okon.model.BoughtProduct;
import com.okon.okon.model.Order;
import com.okon.okon.model.Product;
import com.okon.okon.model.User;
import com.okon.okon.service.OrdersService;
import com.okon.okon.service.ProductService;
import com.okon.okon.service.UserService;
import com.okon.okon.util.RandomProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DBInitializer {

    @Autowired
    public void DBInitialization(ProductService productService, UserService userService,
                                 OrdersService ordersService) {
        initUsers(userService);
        initProducts(productService);
        initOrders(ordersService, userService, productService);
    }

    private void initOrders(OrdersService ordersService, UserService userService, ProductService productService) {
        User user1 = userService.findById(1L).get();
        User user2 = userService.findById(2L).get();
        User user3 = userService.findById(3L).get();
        Product Product1 = productService.findById(1L).get();
        Product Product2 = productService.findById(2L).get();
        Product Product3 = productService.findById(3L).get();
        Product Product4 = productService.findById(4L).get();
        Product Product5 = productService.findById(5L).get();

        BoughtProduct boughtProduct1 = BoughtProduct.builder()
                .product(Product1)
                .price(799L)
                .build();

        BoughtProduct boughtProduct2 = BoughtProduct.builder()
                .product(Product2)
                .price(999L)
                .build();

        BoughtProduct boughtProduct3 = BoughtProduct.builder()
                .product(Product3)
                .price(899L)
                .build();

        BoughtProduct boughtProduct4 = BoughtProduct.builder()
                .product(Product4)
                .price(699L)
                .build();

        BoughtProduct boughtProduct5 = BoughtProduct.builder()
                .product(Product5)
                .price(599L)
                .build();

        boughtProduct1 = ordersService.insertBoughtProduct(boughtProduct1);
        boughtProduct2 = ordersService.insertBoughtProduct(boughtProduct2);
        boughtProduct3 = ordersService.insertBoughtProduct(boughtProduct3);
        boughtProduct4 = ordersService.insertBoughtProduct(boughtProduct4);
        boughtProduct5 = ordersService.insertBoughtProduct(boughtProduct5);

        Order order1 = Order.builder()
                .user(user1)
                .products(List.of(boughtProduct1, boughtProduct2, boughtProduct3))
                .build();

        Order order2 = Order.builder()
                .user(user2)
                .products(List.of(boughtProduct2, boughtProduct3, boughtProduct4, boughtProduct5))
                .build();

        Order order3 = Order.builder()
                .user(user3)
                .products(List.of(boughtProduct4, boughtProduct5))
                .build();

        Order order4 = Order.builder()
                .user(user1)
                .products(List.of(boughtProduct4))
                .build();

        Order order5 = Order.builder()
                .user(user1)
                .products(List.of(boughtProduct5))
                .build();

        ordersService.insert(order1);
        ordersService.insert(order2);
        ordersService.insert(order3);
        ordersService.insert(order4);
        ordersService.insert(order5);
    }

    private void initUsers(UserService userService) {
        User user0 = User.builder()
                .name("admin")
                .surname("admin")
                .build();

        User user1 = User.builder()
                .name("user_1")
                .surname("user_1")
                .build();

        User user2 = User.builder()
                .name("user_2")
                .surname("user_2")
                .build();

        userService.insert(user0);
        userService.insert(user1);
        userService.insert(user2);

    }

    private void initProducts(ProductService productService) {
        RandomProduct.getNotRandomProduct().forEach(productService::insert);
    }
}
