package com.okon.cart.model;

import com.okon.api.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
    private ProductDTO product;
    private int quantity;
    private BigDecimal totalPrice;

    public void calculateTotalPrice() {
        if (quantity > 1) {
            totalPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        } else {
            totalPrice = product.getPrice();
        }
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotalPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem cartItem)) return false;

        return Objects.equals(product, cartItem.product);
    }

    @Override
    public int hashCode() {
        return product != null ? product.hashCode() : 0;
    }
}
