package com.okon.okon.model;

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
        products = Stream
                .concat(old.stream(), Stream.of(item))
                .collect(Collectors.toSet());
    }

    private void removeAndUpdateQuantity(CartItem product, Integer quantity) {
        removeProduct(product.getProduct().getId());
        product.setQuantity(quantity);
    }

    public Optional<CartItem> updateQuantity(Long productId, Integer quantity) {
        Optional<CartItem> product = findItem(productId);
        product.ifPresent(cartItem -> {
            removeAndUpdateQuantity(product.get(), quantity);
            addToProducts(products, product.get());
        });

        return product;
    }

    public void removeProduct(Long productId) {
        products.remove(findItem(productId).get());
    }

    public boolean containsProduct(Long productId) {
        return products.stream().anyMatch(product -> product.getProduct().getId().equals(productId));
    }

    private Optional<CartItem> findItem(Long itemId) {
        return products.stream().filter(p -> p.getProduct().getId().equals(itemId)).findFirst();
    }
}
