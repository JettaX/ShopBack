package com.okon.core.anotations;

public enum Authorities {
    GUEST("GUEST"),
    ADMIN("ADMIN"),
    USER("USER"),
    SUPERADMIN("SUPER-ADMIN");

    private final String value;

    Authorities(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
