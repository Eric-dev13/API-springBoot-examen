package com.api.mushroom.configuration;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
          @NonNull HttpServletRequest request,
          @NonNull HttpServletResponse response,
          @NonNull FilterChain filterChain
    ) throws ServletException, IOException
    {

        // Récupère l'en-tête 'Authorization' de la requête)
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        try {
            // Si l'en-tête 'Authorization' est absent ou ne commence pas par "Bearer "
            if(authHeader == null||!authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // Extrait le token de l'en-tête en retirant les 7 premiers caractères ("Bearer ")
            jwt = authHeader.substring(7);

            // Extrait l'adresse e-mail de l'utilisateur à partir du token décode le token avec la SECRET_KEY (stockée en dur dans JwtService)
            userEmail = jwtService.extractUsername(jwt);

            // Vérification et configuration de l'authentification de Spring Security
            if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Charge les détails de l'utilisateur à partir du service UserDetails
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                // Vérifie si le token est valide pour l'utilisateur
                if(jwtService.isTokenValid(jwt, userDetails)) {
                    // Crée un jeton d'authentification Spring Security avec les détails de l'utilisateur
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null, // Les informations de mot de passe ne sont pas nécessaires car l'authentification est déjà effectuée.
                            userDetails.getAuthorities() // Les rôles/permissions de l'utilisateur
                    );

                    // Ajoute les détails de l'authentification web (adresse IP, navigateur, etc.)
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    // Configure l'authentification de Spring Security pour cet utilisateur
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            logger.info("Une erreur est survenu au cours de l'analyse du token : " + e.getMessage());
        }
        // Poursuit le traitement de la requête
        filterChain.doFilter(request, response);
    }
}
