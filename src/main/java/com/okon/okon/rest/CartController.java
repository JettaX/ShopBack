package com.okon.okon.rest;

import com.okon.okon.model.Cart;
import com.okon.okon.model.CartItem;
import com.okon.okon.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/cart")
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{userId}")
    public Optional<Cart> getCartByUserId(@PathVariable Long userId) {
        log.info("getCartByUserId {}", userId);
        return cartService.findByUserId(userId);
    }

    @PatchMapping("/{userId}/{productId}/{quantity}")
    public Optional<CartItem> updateQuantityByProductIdAndUserId(@PathVariable Long userId, @PathVariable Long productId,
                                                             @PathVariable Integer quantity) {
        log.info("updateQuantityByProductIdAndUserId {}, productId {} and quantity {}", userId, productId, quantity);
        return cartService.updateQuantity(userId, productId, quantity);
    }

    @PostMapping("/{userId}")
    public void addToCart(@PathVariable Long userId, @RequestBody CartItem cartItem) {
        log.info("addToCart {} {}", userId, cartItem);
        cartService.insertToCart(cartItem, userId);
    }

    @DeleteMapping("/clear/{userId}")
    public void clearCartByUserId(@PathVariable Long userId) {
        cartService.clearByUserId(userId);
    }

    @DeleteMapping("/{userId}/{productId}")
    public void removeProductFromCartByUserId(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeProductByUserId(userId, productId);
    }
}
