package com.api.mushroom.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CurrentUserChangePasswordDto(
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Huit caractère au minimum, au moins une lettre majuscule et une lettre minuscule et un chiffre")
        @NotBlank(message = "Le mot de passe est obligatoire !")
        String password,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Huit caractère au minimum, au moins une lettre majuscule et une lettre minuscule et un chiffre")
        @NotBlank(message = "Le nouveau mot de passe est obligatoire !")
        String newPassword
) { }
