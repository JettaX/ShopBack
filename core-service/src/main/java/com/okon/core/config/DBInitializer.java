package com.okon.core.config;

import com.okon.core.model.BoughtProduct;
import com.okon.core.model.Order;
import com.okon.core.model.Product;
import com.okon.core.repository.BoughtProductRepository;
import com.okon.core.service.BoughtProductService;
import com.okon.core.service.OrdersService;
import com.okon.core.service.ProductService;
import com.okon.core.util.RandomProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
public class DBInitializer {
    private final BoughtProductRepository boughtProductRepository;

    public DBInitializer(BoughtProductRepository boughtProductRepository) {
        this.boughtProductRepository = boughtProductRepository;
    }

    @Autowired
    public void DBInitialization(ProductService productService,
                                 OrdersService ordersService, BoughtProductService boughtProductService) {
        initProducts(productService);
        initOrders(ordersService, productService, boughtProductService);
    }

    private void initOrders(OrdersService ordersService, ProductService productService, BoughtProductService boughtProductService) {
        String user1 = "user";
        String user2 = "admin";
        String user3 = "superadmin";
        Product Product1 = productService.findById(1L).get();
        Product Product2 = productService.findById(2L).get();
        Product Product3 = productService.findById(3L).get();
        Product Product4 = productService.findById(4L).get();
        Product Product5 = productService.findById(5L).get();

        BoughtProduct boughtProduct1 = BoughtProduct.builder()
                .productId(Product1.getId())
                .name(Product1.getName())
                .image(Product1.getImage())
                .quantity(9)
                .price(new BigDecimal(799))
                .totalPrice(new BigDecimal(7191))
                .build();

        BoughtProduct boughtProduct2 = BoughtProduct.builder()
                .name(Product2.getName())
                .image(Product2.getImage())
                .price(new BigDecimal(999))
                .quantity(3)
                .totalPrice(new BigDecimal(2997))
                .build();

        BoughtProduct boughtProduct3 = BoughtProduct.builder()
                .productId(Product3.getId())
                .name(Product3.getName())
                .image(Product3.getImage())
                .quantity(1)
                .price(new BigDecimal(899))
                .totalPrice(new BigDecimal(899))
                .build();

        BoughtProduct boughtProduct4 = BoughtProduct.builder()
                .productId(Product4.getId())
                .name(Product4.getName())
                .image(Product4.getImage())
                .quantity(2)
                .price(new BigDecimal(699))
                .totalPrice(new BigDecimal(1398))
                .build();

        BoughtProduct boughtProduct5 = BoughtProduct.builder()
                .productId(Product5.getId())
                .name(Product5.getName())
                .image(Product5.getImage())
                .quantity(6)
                .price(new BigDecimal(599))
                .totalPrice(new BigDecimal(3594))
                .build();

        boughtProduct1 = boughtProductService.insert(boughtProduct1);
        boughtProduct2 = boughtProductService.insert(boughtProduct2);
        boughtProduct3 = boughtProductService.insert(boughtProduct3);
        boughtProduct4 = boughtProductService.insert(boughtProduct4);
        boughtProduct5 = boughtProductService.insert(boughtProduct5);

        Order order1 = Order.builder()
                .username(user1)
                .products(List.of(boughtProduct1, boughtProduct2, boughtProduct3))
                .build();

        Order order11 = Order.builder()
                .username(user1)
                .products(List.of(boughtProduct1))
                .build();

        Order order2 = Order.builder()
                .username(user2)
                .products(List.of(boughtProduct2, boughtProduct3, boughtProduct4, boughtProduct5))
                .build();

        Order order3 = Order.builder()
                .username(user3)
                .products(List.of(boughtProduct4, boughtProduct5))
                .build();

        Order order4 = Order.builder()
                .username(user1)
                .products(List.of(boughtProduct4))
                .build();

        Order order5 = Order.builder()
                .username(user1)
                .products(List.of(boughtProduct5))
                .build();

        order1.calculateTotalPrice();
        order11.calculateTotalPrice();
        order2.calculateTotalPrice();
        order3.calculateTotalPrice();
        order4.calculateTotalPrice();
        order5.calculateTotalPrice();

        ordersService.insert(order1);
        ordersService.insert(order11);
        ordersService.insert(order2);
        ordersService.insert(order3);
        ordersService.insert(order4);
        ordersService.insert(order5);
    }

    private void initProducts(ProductService productService) {
        /*RandomProduct.getRandomProduct(5).forEach(productService::insert);*/
        RandomProduct.getNotRandomProduct().forEach(productService::insert);
    }
}
