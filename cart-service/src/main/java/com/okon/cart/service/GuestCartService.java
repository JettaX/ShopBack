package com.okon.cart.service;

import com.okon.cart.model.Cart;
import com.okon.cart.model.CartItem;

import java.util.Optional;

public interface GuestCartService {
    Cart insertToCart(CartItem product, String guestId);

    Optional<Cart> findByUserId(String guestId);

    void clearByUserId(String guestId);

    void removeProductByUserId(String guestId, Long productId);

    Optional<CartItem> updateQuantity(String guestId, Long productId, Integer quantity);
}
