package com.api.mushroom.auth;

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
    private String email;       // L'adresse e-mail de l'utilisateur pour l'authentification.
    private String  password;   // Le mot de passe de l'utilisateur pour l'authentification.
}
