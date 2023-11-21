package com.api.mushroom.controller.admin.user;

import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;

public record UserDto(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String email,
        String password,
        String pseudo,
        String lastname,
        String firstname,
        String filename,
        boolean isVerified, Collection<? extends GrantedAuthority> getAuthorities
) {
}
