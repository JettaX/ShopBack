package com.okon.core.integration;

import com.okon.api.dto.CartDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartServiceIntegration {
    private final RestTemplate restTemplate;
    @Value("${api.cart.url}")
    private String baseUrl;
    private final String urlSeparator = "/";
    private final String urlMethodClear = "clear";

    public Optional<CartDTO> findByUserId(Long id) {
        log.info("find cart by user id: " + id);
        CartDTO cartDTO = restTemplate.getForObject(
                baseUrl + urlSeparator + id, CartDTO.class);
        return Optional.ofNullable(cartDTO);
    }

    public void clearByUserId(Long id) {
        restTemplate.getForObject(baseUrl + urlSeparator + urlMethodClear + urlSeparator + id, CartDTO.class);
    }
}
