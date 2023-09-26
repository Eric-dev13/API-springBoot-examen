package com.api.mushroom.controller.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String pseudo;
    private Collection<? extends GrantedAuthority> authorities;
    private String lastname;
    private String firstname;
    private String email;
    private String filename;


}
