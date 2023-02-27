package com.okon.core.integration;

import com.okon.api.dto.CartDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public Optional<CartDTO> findByUserId(String id) {
        return Optional.ofNullable(cartServiceWebClient.get()
                .uri("")
                .header("username", id)
                .retrieve()
                .bodyToMono(CartDTO.class)
                .block());
    }

    public void clearByUserId(String id) {
        cartServiceWebClient.get()
                .uri("/clear")
                .header("username", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
