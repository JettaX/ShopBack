package com.okon.okon.config;

import com.okon.okon.model.*;
import com.okon.okon.repository.BoughtProductRepository;
import com.okon.okon.repository.RoleRepository;
import com.okon.okon.service.BoughtProductService;
import com.okon.okon.service.OrdersService;
import com.okon.okon.service.ProductService;
import com.okon.okon.service.UserService;
import com.okon.okon.util.RandomProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DBInitializer {
    private final BoughtProductRepository boughtProductRepository;

    public DBInitializer(BoughtProductRepository boughtProductRepository) {
        this.boughtProductRepository = boughtProductRepository;
    }

    @Autowired
    public void DBInitialization(ProductService productService, UserService userService,
                                 OrdersService ordersService, RoleRepository roleRepository, BoughtProductService boughtProductService) {
        initUsers(userService, roleRepository);
        initProducts(productService);
        initOrders(ordersService, userService, productService, boughtProductService);
    }

    private void initOrders(OrdersService ordersService, UserService userService, ProductService productService, BoughtProductService boughtProductService) {
        User user1 = userService.findById(1L).get();
        User user2 = userService.findById(2L).get();
        User user3 = userService.findById(3L).get();
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
                .price(799L)
                .build();

        BoughtProduct boughtProduct2 = BoughtProduct.builder()
                .name(Product2.getName())
                .image(Product2.getImage())
                .price(999L)
                .quantity(3)
                .build();

        BoughtProduct boughtProduct3 = BoughtProduct.builder()
                .productId(Product3.getId())
                .name(Product3.getName())
                .image(Product3.getImage())
                .quantity(1)
                .price(899L)
                .build();

        BoughtProduct boughtProduct4 = BoughtProduct.builder()
                .productId(Product4.getId())
                .name(Product4.getName())
                .image(Product4.getImage())
                .quantity(2)
                .price(699L)
                .build();

        BoughtProduct boughtProduct5 = BoughtProduct.builder()
                .productId(Product5.getId())
                .name(Product5.getName())
                .image(Product5.getImage())
                .quantity(6)
                .price(599L)
                .build();

        boughtProduct1 = boughtProductService.insert(boughtProduct1);
        boughtProduct2 = boughtProductService.insert(boughtProduct2);
        boughtProduct3 = boughtProductService.insert(boughtProduct3);
        boughtProduct4 = boughtProductService.insert(boughtProduct4);
        boughtProduct5 = boughtProductService.insert(boughtProduct5);

        Order order1 = Order.builder()
                .user(user1)
                .products(List.of(boughtProduct1, boughtProduct2, boughtProduct3))
                .build();

        Order order11 = Order.builder()
                .user(user1)
                .products(List.of(boughtProduct1))
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
        ordersService.insert(order11);
        ordersService.insert(order2);
        ordersService.insert(order3);
        ordersService.insert(order4);
        ordersService.insert(order5);
    }

    private void initUsers(UserService userService, RoleRepository roleRepository) {
        Role roleUser = Role.builder().name("USER").build();
        roleUser = roleRepository.save(roleUser);
        Role roleAdmin = Role.builder().name("ADMIN").build();
        roleAdmin = roleRepository.save(roleAdmin);
        Role roleSUPERAdmin = Role.builder().name("SUPER_ADMIN").build();
        roleSUPERAdmin = roleRepository.save(roleSUPERAdmin);

        User user0 = User.builder()
                .name("superadmin")
                .surname("superadmin")
                .username("superadmin")
                .password("$2a$12$s/m3PPvNG.6l.MmCf.TdR.z.xuf.bSQT0UhRLpfgtSOnPBWC0TlcC")
                .roles(List.of(roleSUPERAdmin))
                .build();

        User user1 = User.builder()
                .name("admin")
                .surname("admin")
                .username("admin")
                .password("$2a$12$s/m3PPvNG.6l.MmCf.TdR.z.xuf.bSQT0UhRLpfgtSOnPBWC0TlcC")
                .roles(List.of(roleAdmin))
                .build();

        User user2 = User.builder()
                .name("user")
                .surname("user")
                .username("user")
                .password("$2a$12$s/m3PPvNG.6l.MmCf.TdR.z.xuf.bSQT0UhRLpfgtSOnPBWC0TlcC")
                .roles(List.of(roleUser))
                .build();

        userService.insert(user0);
        userService.insert(user1);
        userService.insert(user2);

    }

    private void initProducts(ProductService productService) {
        RandomProduct.getRandomProduct(5).forEach(productService::insert);
    }
}
