package com.api.mushroom.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionStorageDTO {
    private String username;
    private String pseudo;
    private Collection<? extends GrantedAuthority> roles;
    private String filename;
}

