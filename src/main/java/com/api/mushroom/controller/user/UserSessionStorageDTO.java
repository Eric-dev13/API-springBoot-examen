package com.api.mushroom.controller.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public record UserSessionStorageDTO(
        String username,
        String pseudo,
        Collection<? extends GrantedAuthority> roles,
        String filename
){ }

