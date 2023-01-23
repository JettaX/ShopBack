package com.okon.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config> {
    @Autowired
    private JwtUtil jwtUtil;
    private static final String IDENTITY_USER_HEADERS = "username";
    private static final String IDENTITY_ROLE_HEADERS = "roles";

    public JwtAuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        log.info("JwtAuthFilter.apply()");
        return (exchange, chain) -> {
            ServerHttpRequest request = checkIfExistsHeadersAndDelete(exchange.getRequest());
            if (!isAuthMissing(request)) {
                final String token = getAuthHeader(request);
                if (jwtUtil.isInvalid(token)) {
                    return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
                }
                populateRequestWithHeaders(exchange, token);
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {
    }

    private ServerHttpRequest checkIfExistsHeadersAndDelete(ServerHttpRequest request) {
        if (request.getHeaders().containsKey(IDENTITY_USER_HEADERS)) {
            request.getHeaders().remove(IDENTITY_USER_HEADERS);
        }
        if (request.getHeaders().containsKey(IDENTITY_ROLE_HEADERS)) {
            request.getHeaders().remove(IDENTITY_ROLE_HEADERS);
        }
        return request;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        log.info("JwtAuthFilter.onError()");
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0).substring(7);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        if (!request.getHeaders().containsKey("Authorization")) {
            return true;
        }
        if (!request.getHeaders().getOrEmpty("Authorization").get(0).startsWith("Bearer ")) {
            return true;
        }
        return false;
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        exchange.getRequest().mutate()
                .header(IDENTITY_USER_HEADERS, jwtUtil.getUsernameFromToken(token))
//                .header("role", String.valueOf(claims.get("role")))
                .build();
    }
}
