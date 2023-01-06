package com.okon.cart.integration;

import com.okon.api.dto.ProductDTO;
import com.okon.api.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public ProductDTO getProductById(Long id) {
        return productServiceWebClient.get()
                .uri("/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Product not found in product MC")))
                .bodyToMono(ProductDTO.class)
                .block();
    }
}
