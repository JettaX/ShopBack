package com.okon.cart.controller;


import com.okon.api.dto.CartDTO;
import com.okon.api.dto.CartItemDTO;
import com.okon.api.exceptions.ResourceNotFoundException;
import com.okon.cart.convertors.CartConvertor;
import com.okon.cart.convertors.CartItemConvertor;
import com.okon.cart.model.CartItem;
import com.okon.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/cart")
public class CartController {
    private final CartService cartService;
    private final CartConvertor cartConvertor;
    private final CartItemConvertor cartItemConvertor;

    @GetMapping("/{userId}")
    public Optional<CartDTO> getCartByUserId(@PathVariable Long userId) {
        log.info("getCartByUserId {}", userId);
        return Optional.ofNullable(cartConvertor.convertToDTO(cartService.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user's cart does not exist"))));
    }

    @PatchMapping("/{userId}/{productId}/{quantity}")
    public Optional<CartItemDTO> updateQuantityByProductIdAndUserId(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @PathVariable Integer quantity) {
        log.info("updateQuantityByProductIdAndUserId {}, productId {} and quantity {}", userId, productId, quantity);
        return Optional
                .ofNullable(cartItemConvertor
                        .convertToDTO(cartService
                                .updateQuantity(userId, productId, quantity)
                                .orElseThrow(() -> new ResourceNotFoundException("Update quantity by product failed"))));
    }

    @PostMapping("/{userId}")
    public void addToCart(
            @PathVariable Long userId,
            @RequestBody CartItem cartItem) {
        log.info("addToCart {} {}", userId, cartItem);
        cartService.insertToCart(cartItem, userId);
    }

    @GetMapping("/clear/{userId}")
    public void clearCartByUserId(@PathVariable Long userId) {
        cartService.clearByUserId(userId);
    }

    @DeleteMapping("/{userId}/{productId}")
    public void removeProductFromCartByUserId(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeProductByUserId(userId, productId);
    }
}
