package com.okon.core.controller;


import com.okon.core.model.Order;
import com.okon.core.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrdersService ordersService;

    @GetMapping("/getUserOrders")
    public List<Order> getUserOrders(@RequestHeader String username) {
        log.info("get user orders: " + username);
        return ordersService.findUserOrders(username);
    }

    @GetMapping("/getCountOfBought/{id}")
    public Long getCountOfBought(@PathVariable Long id) {
        log.info("get count of bought: " + id);
        return ordersService.getCountOfBought(id);
    }

    @PostMapping()
    public void createOrder(@RequestHeader String username, @RequestHeader String authorization) {
        log.info("create order for user {}", username);
        ordersService.createOrder(username, authorization);
    }
}
