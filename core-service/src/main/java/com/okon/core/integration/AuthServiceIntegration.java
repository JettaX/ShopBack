package com.okon.core.integration;

import com.okon.api.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthServiceIntegration {
    private final WebClient usersServiceWebClient;

    public Optional<UserDTO> findByUsername(String username, String token) {
        return Optional.ofNullable(usersServiceWebClient.get()
                .uri("/username/" + username)
                .headers(httpHeaders -> httpHeaders.set("Authorization", token))
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block());
    }

}
