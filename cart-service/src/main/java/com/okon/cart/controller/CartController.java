package com.okon.cart.controller;


import com.okon.api.dto.CartDTO;
import com.okon.api.dto.CartItemDTO;
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

    @GetMapping("/{cartId}")
    public Optional<CartDTO> getCartByUserId(@PathVariable String cartId) {
        log.info("getCartByUserId {}", cartId);
        return Optional.ofNullable(cartConvertor.convertToDTO(cartService.find(cartId)));
    }

    @PatchMapping("/{cartId}/{productId}/{quantity}")
    public Optional<CartItemDTO> updateQuantityByProductIdAndUserId(
            @PathVariable String cartId,
            @PathVariable Long productId,
            @PathVariable Integer quantity) {
        log.info("updateQuantityByProductIdAndUserId {}, productId {} and quantity {}", cartId, productId, quantity);
        return Optional
                .ofNullable(cartItemConvertor
                        .convertToDTO(cartService
                                .updateQuantity(cartId, productId, quantity)));
    }

    @PostMapping("/{cartId}")
    public void addToCart(
            @PathVariable String cartId,
            @RequestBody CartItem cartItem) {
        log.info("addToCart {} {}", cartId, cartItem);
        cartService.insertToCart(cartItem, cartId);
    }

    @GetMapping("/clear/{cartId}")
    public void clearCartByUserId(@PathVariable String cartId) {
        cartService.clear(cartId);
    }

    @DeleteMapping("/{cartId}/{productId}")
    public void removeProductFromCartByUserId(@PathVariable String cartId, @PathVariable Long productId) {
        cartService.removeProduct(cartId, productId);
    }

    @PostMapping("/merge/{cartId}/{guestId}")
    public void mergeFromGuestCart(@PathVariable String cartId, @PathVariable String guestId) {
        cartService.mergeFromGuestCart(cartId, guestId);
    }
}
