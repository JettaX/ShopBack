package com.okon.cart.controller;


import com.okon.api.dto.CartDTO;
import com.okon.api.dto.CartItemDTO;
import com.okon.cart.convertors.CartConvertor;
import com.okon.cart.convertors.CartItemConvertor;
import com.okon.cart.model.Cart;
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

    @GetMapping()
    public Optional<CartDTO> getCart(
            @RequestHeader(required = false) String username,
            @RequestHeader(required = false) String guestId
    ) {
        String cartId = getCartId(username, guestId);
        log.info("get cart by id: {}", cartId);
        Cart cart = cartService.find(cartId);
        if (cart.getProducts() == null || cart.getProducts().isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(cartConvertor.convertToDTO(cart));
    }

    @PatchMapping("/{productId}/{quantity}")
    public Optional<CartItemDTO> updateQuantityByProductIdAndUserId(
            @RequestHeader(required = false) String username,
            @RequestHeader(required = false) String guestId,
            @PathVariable Long productId,
            @PathVariable Integer quantity) {
        String cartId = getCartId(username, guestId);
        log.info("update quantity by product id: {} and cart id: {}", productId, cartId);
        return Optional
                .ofNullable(cartItemConvertor
                        .convertToDTO(cartService
                                .updateQuantity(cartId, productId, quantity)));
    }

    @PostMapping()
    public void addToCart(
            @RequestHeader(required = false) String username,
            @RequestHeader(required = false) String guestId,
            @RequestBody CartItem cartItem) {
        String cartId = getCartId(username, guestId);
        log.info("add to cart by cart id: {}", cartId);
        cartService.insertToCart(cartItem, cartId);
    }

    @GetMapping("/clear")
    public void clearCartByUserId(
            @RequestHeader(required = false) String username,
            @RequestHeader(required = false) String guestId
    ) {
        String cartId = getCartId(username, guestId);
        log.info("clear cart by id: {}", cartId);
        cartService.clear(cartId);
    }

    @DeleteMapping("/{productId}")
    public void removeProductFromCartByUserId(
            @RequestHeader(required = false) String username,
            @RequestHeader(required = false) String guestId,
            @PathVariable Long productId) {
        String cartId = getCartId(username, guestId);
        log.info("remove product by id: {} from cart by id: {}", productId, cartId);
        cartService.removeProduct(cartId, productId);
    }

    @PostMapping("/merge/{guestId}")
    public void mergeFromGuestCart(
            @RequestHeader String username,
            @PathVariable String guestId
    ) {
        log.info("merge from guest cart by id: {} and username: {}", guestId, username);
        cartService.mergeFromGuestCart(username, guestId);
    }

    private String getCartId(String username, String guestId) {
        return Optional.ofNullable(username).orElse(guestId);
    }
}
