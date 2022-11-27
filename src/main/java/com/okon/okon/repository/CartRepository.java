package com.okon.okon.repository;

import com.okon.okon.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);

    void removeAllByUserId(Long userId);
}
