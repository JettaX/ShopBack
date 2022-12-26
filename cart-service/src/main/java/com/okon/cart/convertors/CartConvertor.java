package com.okon.cart.convertors;

import com.okon.api.dto.CartDTO;
import com.okon.cart.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartConvertor {
    private final CartItemConvertor itemConvertor;

    public CartDTO convertToDTO(Cart cart) {
        return CartDTO.builder()
                .userId(cart.getUserId())
                .products(itemConvertor.convertToDTO(cart.getProducts()))
                .build();
    }
}
