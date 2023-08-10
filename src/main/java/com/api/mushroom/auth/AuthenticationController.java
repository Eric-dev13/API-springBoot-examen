package com.api.mushroom.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur pour gérer les opérations d'authentification (enregistrement et authentification des utilisateurs).
 */
@RequiredArgsConstructor
@RestController
@CrossOrigin

@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * Point d'accès pour l'enregistrement d'un nouvel utilisateur.
     *
     * @param request Objet RegisterRequest contenant les données d'enregistrement de l'utilisateur.
     * @return Réponse ResponseEntity avec le corps contenant l'objet AuthenticationResponse si l'enregistrement est réussi.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        // Appelle la méthode register() du service d'authentification pour effectuer l'enregistrement.
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     * Point d'accès pour l'authentification d'un utilisateur existant.
     *
     * @param request Objet AuthenticationRequest contenant les informations d'authentification de l'utilisateur (email, mot de passe, etc.).
     * @return Réponse ResponseEntity avec le corps contenant l'objet AuthenticationResponse si l'authentification est réussie.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        // Appelle la méthode authenticate() du service d'authentification pour effectuer l'authentification.
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
