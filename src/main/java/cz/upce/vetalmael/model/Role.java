package cz.upce.vetalmael.model;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public enum Role implements Serializable, GrantedAuthority {
    ADMINISTRATOR("ADMINISTRATOR"),
    CLIENT("CLIENT"),
    VETERINARY("VETERINARY"),
    VETERINARY_TECHNICIAN("VETERINARY_TECHNICIAN");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
