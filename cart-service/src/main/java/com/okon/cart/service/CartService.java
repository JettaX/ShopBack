package com.okon.cart.service;


import com.okon.cart.model.Cart;
import com.okon.cart.model.CartItem;

public interface CartService {

    Cart insertToCart(CartItem product, String cartId);

    Cart find(String cartId);

    void clear(String cartId);

    void removeProduct(String cartId, Long productId);

    CartItem updateQuantity(String cartId, Long productId, Integer quantity);

    void mergeFromGuestCart(String cartId, String guestId);
}
