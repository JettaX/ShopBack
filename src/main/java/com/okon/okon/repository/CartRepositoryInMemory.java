package com.okon.okon.repository;

import com.okon.okon.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CartRepositoryInMemory implements CartRepository {
    private static Map<Long, Cart> carts = new HashMap<>();

    @Override
    public Cart insert(Cart cart) {
        return carts.put(cart.getUser().getId(),cart);
    }

    @Override
    public Optional<Cart> findByUserId(Long userId) {
        return Optional.ofNullable(carts.get(userId));
    }

    @Override
    public void clearByUserId(Long userId) {
        carts.remove(userId);
    }
}
