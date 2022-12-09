package com.okon.okon.service;

import com.okon.okon.model.Cart;
import com.okon.okon.model.CartItem;

import java.util.Optional;

public interface CartService {

    Cart insertToCart(CartItem product, Long userId);

    Optional<Cart> findByUserId(Long userId);

    void clearByUserId(Long userId);

    void removeProductByUserId(Long userId, Long productId);

    Optional<CartItem> updateQuantity(Long userId, Long productId, Integer quantity);
}
