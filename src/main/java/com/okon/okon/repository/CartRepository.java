package com.okon.okon.repository;

import com.okon.okon.model.Cart;

import java.util.Optional;

public interface CartRepository {
    Cart insert(Cart cart);

    Optional<Cart> findByUserId(String userId);

    void clearByUserId(String userId);
}
