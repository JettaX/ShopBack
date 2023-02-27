package com.okon.core.listenres;

import com.okon.core.integration.CartServiceIntegration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartListener {
    private final CartServiceIntegration cartServiceIntegration;

    @EventListener(CartClearEvent.class)
    public void handleCartClearEvent(CartClearEvent event) {
        log.debug("Cart clear event received for user: {}", event.getUsername());
        cartServiceIntegration.clearByUserId(event.getUsername());
    }
}
