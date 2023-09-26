package com.api.mushroom.controller.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String pseudo;
    private String lastname;
    private String firstname;
    private String filename;


}
