package com.okon.okon.service;

import com.okon.okon.model.Cart;
import com.okon.okon.model.CartItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    private final Map<Long, Cart> carts = new HashMap<>();

    @Override
    public Cart insertToCart(CartItem product, Long userId) {
        Cart cart = carts.get(userId);
        if (cart == null) {
            cart = Cart.builder()
                    .userId(userId)
                    .products(Set.of(product))
                    .build();
        } else {
            cart.addProduct(product);
        }
        return carts.put(userId, cart);
    }


    @Override
    public Optional<Cart> findByUserId(Long userId) {
        return Optional.ofNullable(carts.get(userId));
    }

    @Override
    public void clearByUserId(Long userId) {
        carts.remove(userId);
    }

    @Override
    public void removeProductByUserId(Long userId, Long productId) {
        Cart cart = carts.get(userId);
        if (cart != null) {
            cart.removeProduct(productId);
            carts.put(userId, cart);
        }
    }

    @Override
    public Optional<CartItem> updateQuantity(Long userId, Long productId, Integer quantity) {
        Cart cart = findByUserId(userId).orElseThrow();
        return cart.updateQuantity(productId, quantity);
    }
}
