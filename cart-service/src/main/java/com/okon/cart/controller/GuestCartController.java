package com.okon.cart.controller;

import com.okon.api.dto.CartDTO;
import com.okon.api.dto.CartItemDTO;
import com.okon.api.exceptions.ResourceNotFoundException;
import com.okon.cart.convertors.CartConvertor;
import com.okon.cart.convertors.CartItemConvertor;
import com.okon.cart.model.CartItem;
import com.okon.cart.service.GuestCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/cart/guest")
public class GuestCartController {

    private final GuestCartService guestCartService;
    private final CartConvertor cartConvertor;
    private final CartItemConvertor cartItemConvertor;

    @GetMapping("/{guestId}")
    public Optional<CartDTO> getCartByUserId(@PathVariable String guestId) {
        log.info("getCartByUserId {}", guestId);
        return Optional.ofNullable(cartConvertor.convertToDTO(guestCartService.findByUserId(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("user's cart does not exist"))));
    }

    @PatchMapping("/{guestId}/{productId}/{quantity}")
    public Optional<CartItemDTO> updateQuantityByProductIdAndUserId(
            @PathVariable String guestId,
            @PathVariable Long productId,
            @PathVariable Integer quantity) {
        log.info("updateQuantityByProductIdAndUserId {}, productId {} and quantity {}", guestId, productId, quantity);
        return Optional
                .ofNullable(cartItemConvertor
                        .convertToDTO(guestCartService
                                .updateQuantity(guestId, productId, quantity)
                                .orElseThrow(() -> new ResourceNotFoundException("Update quantity by product failed"))));
    }

    @PostMapping("/{guestId}")
    public void addToCart(
            @PathVariable String guestId,
            @RequestBody CartItem cartItem) {
        log.info("addToCart {} {}", guestId, cartItem);
        guestCartService.insertToCart(cartItem, guestId);
    }

    @GetMapping("/clear/{guestId}")
    public void clearCartByUserId(@PathVariable String guestId) {
        guestCartService.clearByUserId(guestId);
    }

    @DeleteMapping("/{guestId}/{productId}")
    public void removeProductFromCartByUserId(@PathVariable String guestId, @PathVariable Long productId) {
        guestCartService.removeProductByUserId(guestId, productId);
    }

    @GetMapping("/generateId")
    public String getIdForGuest() {
        return UUID.randomUUID().toString();
    }
}
