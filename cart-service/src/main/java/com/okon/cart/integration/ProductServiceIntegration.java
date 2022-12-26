package com.okon.cart.integration;

import com.okon.api.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;
    @Value("${api.products.url}")
    private String baseUrl;
    private final String urlSeparator = "/";

    public Optional<ProductDTO> getProductById(Long id) {
        ProductDTO productDTO = restTemplate.getForObject(baseUrl + urlSeparator + id, ProductDTO.class);
        return Optional.ofNullable(productDTO);
    }
}
