package com.okon.okon.service;

import com.okon.okon.model.Cart;
import com.okon.okon.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public Cart insert(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> findByUserId(Long userId) {
        log.debug("findById {}", userId);
        return cartRepository.findByUserId(userId);
    }

    @Override
    public void clearByUserId(Long userId) {
        log.debug("clearByUserId {}", userId);
        cartRepository.removeAllByUserId(userId);
    }
}
