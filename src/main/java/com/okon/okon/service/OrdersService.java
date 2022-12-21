package com.okon.okon.service;

import com.okon.okon.model.Order;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface OrdersService {
    Order insert(Order order);
    List<Order> findUserOrders(Long userId);
    Long getCountOfBought(Long productId);
    Optional<Order> findById(Long id);
    void createOrder(Principal principal);
}
