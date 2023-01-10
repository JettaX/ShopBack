package com.okon.cart.convertors;

import com.okon.api.dto.CartItemDTO;
import com.okon.cart.model.CartItem;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CartItemConvertor {

    public CartItemDTO convertToDTO(CartItem cartItem) {
        return CartItemDTO.builder()
                .product(cartItem.getProduct())
                .quantity(cartItem.getQuantity())
                .totalPrice(cartItem.getTotalPrice())
                .build();
    }

    public Set<CartItemDTO> convertToDTO(Set<CartItem> cartItems) {
        return cartItems.stream().map(this::convertToDTO).collect(Collectors.toSet());
    }
}
