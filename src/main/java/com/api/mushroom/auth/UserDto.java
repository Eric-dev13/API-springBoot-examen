package com.api.mushroom.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record UserDto(
        String username,
        String pseudo,
        Collection<? extends GrantedAuthority> getAuthorities,
        String filename
) {
}
