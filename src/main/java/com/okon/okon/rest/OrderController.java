package com.okon.okon.rest;

import com.okon.okon.model.Order;
import com.okon.okon.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/orders")
@CrossOrigin("*")
public class OrderController {
    private final OrdersService ordersService;

    @GetMapping("/getUserOrders/{id}")
    public List<Order> getUserOrders(@PathVariable Long id) {
        return ordersService.findByUserId(id);
    }

    @GetMapping("/getCountOfBought/{id}")
    public Long getCountOfBought(@PathVariable Long id) {
        return ordersService.getCountOfBought(id);
    }
}
