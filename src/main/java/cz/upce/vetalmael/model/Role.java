package cz.upce.vetalmael.model;

import org.springframework.security.core.GrantedAuthority;
import java.io.Serializable;

public enum Role implements Serializable,GrantedAuthority {
    ADMIN("ADMIN"),USER("USER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
