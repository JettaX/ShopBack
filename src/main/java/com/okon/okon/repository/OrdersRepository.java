package com.okon.okon.repository;

import com.okon.okon.model.BoughtProduct;
import com.okon.okon.model.Order;
import com.okon.okon.model.User;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository {
    Order insert(Order order);

    BoughtProduct insertBoughtProduct(BoughtProduct product);
    List<Order> findByUserId(Long userId);
    Optional<Order> findById(Long id);
    List<User> findUsersByProduct(Long productId);
}
