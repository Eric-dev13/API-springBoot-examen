package com.api.mushroom.controller.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;


public record UserDTO(String pseudo, String lastname, String firstname, String filename){ }

