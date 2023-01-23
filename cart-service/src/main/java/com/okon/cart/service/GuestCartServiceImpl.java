package com.okon.cart.service;

import com.okon.cart.model.Cart;
import com.okon.cart.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class GuestCartServiceImpl implements GuestCartService {
    private final Map<String, Cart> guestsCarts = new HashMap<>();

    @Override
    public Cart insertToCart(CartItem product, String guestId) {
        Cart cart = guestsCarts.get(guestId);
        if (cart == null) {
            cart = new Cart();
        }
        cart.addProduct(product);
        return guestsCarts.put(guestId, cart);
    }


    @Override
    public Optional<Cart> findByUserId(String guestId) {
        return Optional.ofNullable(guestsCarts.get(guestId));
    }

    @Override
    public void clearByUserId(String guestId) {
        guestsCarts.remove(guestId);
    }

    @Override
    public void removeProductByUserId(String guestId, Long productId) {
        Cart cart = guestsCarts.get(guestId);
        if (cart != null) {
            cart.removeProduct(productId);
            guestsCarts.put(guestId, cart);
        }
    }

    @Override
    public Optional<CartItem> updateQuantity(String guestId, Long productId, Integer quantity) {
        Cart cart = findByUserId(guestId).orElseThrow();
        return cart.updateQuantity(productId, quantity);
    }
}
