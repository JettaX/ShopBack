package com.okon.okon.repository;

import com.okon.okon.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CartRepositoryImpl implements CartRepository {
    private static Map<String, Cart> carts = new HashMap<>();

    @Override
    public Cart insert(Cart cart) {
        return carts.put(cart.getUserId(),cart);
    }

    @Override
    public Optional<Cart> findByUserId(String userId) {
        return Optional.ofNullable(carts.get(userId));
    }

    @Override
    public void clearByUserId(String userId) {
        carts.remove(userId);
    }
}
