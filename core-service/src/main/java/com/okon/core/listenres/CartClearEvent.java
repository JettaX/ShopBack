package com.okon.core.listenres;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CartClearEvent extends ApplicationEvent {
    private final String username;

    public CartClearEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

}
