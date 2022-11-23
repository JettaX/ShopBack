package com.okon.okon.service;

import com.okon.okon.model.Cart;

import java.util.Optional;

public interface CartService {

    Cart insert(Cart cart);

    Optional<Cart> findByUserId(Long userId);

    void clearByUserId(Long userId);
}
