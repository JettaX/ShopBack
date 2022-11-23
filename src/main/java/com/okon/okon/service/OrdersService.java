package com.okon.okon.service;

import com.okon.okon.model.BoughtProduct;
import com.okon.okon.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrdersService {
    Order insert(Order order);
    BoughtProduct insertBoughtProduct(BoughtProduct product);
    List<Order> findByUserId(Long userId);
    Long getCountOfBought(Long productId);
    Optional<Order> findById(Long id);
}
