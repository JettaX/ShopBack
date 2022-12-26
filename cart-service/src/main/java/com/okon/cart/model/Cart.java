package com.okon.cart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    private Long userId;
    private Set<CartItem> products = new HashSet<>();

    public void addProduct(CartItem product) {
        Optional<CartItem> item = findItem(product.getProduct().getId());
        item.ifPresent(cartItem -> removeAndUpdateQuantity(product, cartItem.getQuantity() + 1));
        addToProducts(products, product);
    }

    private void addToProducts(Set<CartItem> old, CartItem item) {
        if (!old.isEmpty()) {
            products = Stream
                    .concat(old.stream(), Stream.of(item))
                    .collect(Collectors.toSet());
        } else {
            products = new HashSet<>(Set.of(item));
        }
    }

    private void removeAndUpdateQuantity(CartItem product, Integer quantity) {
        removeProduct(product.getProduct().getId());
        product.setQuantity(quantity);
    }

    public Optional<CartItem> updateQuantity(Long productId, Integer quantity) {
        Optional<CartItem> product = findItem(productId);
        product.ifPresent(cartItem -> {
            removeAndUpdateQuantity(cartItem, quantity);
            addToProducts(products, cartItem);
        });
        return product;
    }

    public void removeProduct(Long productId) {
        Optional<CartItem> item = findItem(productId);
        if (products.size() == 1 && item.isPresent()) {
            products = new HashSet<>();
        } else {
            products.remove(item.get());
        }
    }

    public boolean containsProduct(Long productId) {
        return products.stream().anyMatch(product -> product.getProduct().getId().equals(productId));
    }

    private Optional<CartItem> findItem(Long itemId) {
        return products.stream().filter(p -> p.getProduct().getId().equals(itemId)).findFirst();
    }
}
