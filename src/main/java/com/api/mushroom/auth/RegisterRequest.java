package com.api.mushroom.auth;

import com.api.mushroom.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String pseudo;
    private Role role;
    private String lastname;
    private String firstname;
    private String email;
    private String password;
    private String avatar;
    private Boolean isVerified;

}
