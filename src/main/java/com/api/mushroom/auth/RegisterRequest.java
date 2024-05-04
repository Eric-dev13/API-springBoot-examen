package com.api.mushroom.auth;

import com.api.mushroom.security.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @Email(message = "L'adresse e-mail n'est pas valide !")
    @NotBlank(message = "L'adresse mail est obligatoire !")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire !")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
        message = "Huit caractère au minimum, au moins une lettre majuscule et une lettre minuscule et un chiffre !"
    )
    private String password;

    private String avatar;

    private Boolean isVerified;

}
