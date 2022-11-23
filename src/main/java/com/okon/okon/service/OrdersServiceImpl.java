package com.okon.okon.service;

import com.okon.okon.model.BoughtProduct;
import com.okon.okon.model.Order;
import com.okon.okon.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;

    @Override
    public Order insert(Order order) {
        return ordersRepository.insert(order);
    }

    @Override
    public BoughtProduct insertBoughtProduct(BoughtProduct product) {
        return ordersRepository.insertBoughtProduct(product);
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return ordersRepository.findByUserId(userId);
    }

    @Override
    public Long getCountOfBought(Long productId) {
        return Long.valueOf(ordersRepository.findUsersByProduct(productId).size());
    }

    @Override
    public Optional<Order> findById(Long id) {
        return ordersRepository.findById(id);
    }


}
