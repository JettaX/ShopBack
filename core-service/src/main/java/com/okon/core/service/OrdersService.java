package com.okon.core.service;


import com.okon.core.model.Order;

import java.util.List;
import java.util.Optional;


public interface OrdersService {
    Order insert(Order order);
    List<Order> findUserOrders(String username);
    Long getCountOfBought(Long productId);
    Optional<Order> findById(Long id);
    void createOrder(String username, String token);
}
