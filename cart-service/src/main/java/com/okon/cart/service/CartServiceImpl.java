package com.okon.cart.service;

import com.okon.cart.model.Cart;
import com.okon.cart.model.CartItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final RedisTemplate<String, Cart> carts;

    @Override
    public Cart insertToCart(CartItem product, String cartId) {
        Cart cart = find(cartId);
        cart.addProduct(product);
        return carts.opsForValue().getAndSet(cartId, cart);
    }


    @Override
    public Cart find(String cartId) {
        if (Boolean.FALSE.equals(carts.hasKey(cartId))) {
            carts.opsForValue().set(cartId, Cart.builder().userId(cartId).build());
        }
        return carts.opsForValue().get(cartId);
    }

    @Override
    public void clear(String cartId) {
        carts.delete(cartId);
    }

    @Override
    public void removeProduct(String cartId, Long productId) {
        Cart cart = find(cartId);
        cart.removeProduct(productId);
        carts.opsForValue().set(cartId, cart);

    }

    @Override
    public CartItem updateQuantity(String cartId, Long productId, Integer quantity) {
        Cart cart = find(cartId);
        CartItem cartItem = cart.updateQuantity(productId, quantity).orElseThrow();
        carts.opsForValue().set(cartId, cart);
        return cartItem;
    }

    @Override
    public void mergeFromGuestCart(String cartId, String guestId) {
        Cart guestCart = find(guestId);
        if (!guestCart.getProducts().isEmpty()) {
            Cart cart = find(cartId);
            for (CartItem item : guestCart.getProducts()) {
                cart.addProduct(item);
            }
            carts.opsForValue().set(cartId, cart);
        }
        clear(guestId);
    }
}
