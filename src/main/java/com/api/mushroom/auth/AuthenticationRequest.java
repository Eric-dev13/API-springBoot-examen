package com.api.mushroom.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette classe représente une demande d'authentification.
 * Elle est utilisée pour encapsuler les informations nécessaires
 * pour authentifier un utilisateur, telles que l'adresse e-mail
 * et le mot de passe.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @Email(message = "L'adresse e-mail n'est pas valide")
    @NotBlank(message = "L'adresse mail est obligatoire !")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Huit caractère au minimum, au moins une lettre majuscule et une lettre minuscule et un chiffre")
    @NotBlank(message = "Le mot de passe est obligatoire !")
    private String  password;

}
