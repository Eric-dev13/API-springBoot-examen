package com.api.mushroom.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private  final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    // https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html#migrate-authorize-requests
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Le http.securityMatcher indique que ce HttpSecurity s’applique uniquement aux URL commençant /api/.
            .securityMatcher("/api/**")
            // Active la configuration de CORS (Cross-Origin Resource Sharing) pour gérer les requêtes depuis des origines différentes.
            .cors()
            .and()
            // Désactive la protection CSRF (Cross-Site Request Forgery), qui est généralement désactivée pour les API.
            .csrf().disable()
            // Définit les règles d'autorisation pour les requêtes
            // Configure la gestion des sessions.
            .sessionManagement()
            //  Définit la politique de création de sessions comme étant sans état (stateless). Cela signifie que Spring Security ne crée pas de session HTTP pour les utilisateurs. Les tokens d'authentification sont utilisés pour authentifier les utilisateurs.
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // Configure le fournisseur d'authentification.
            .authenticationProvider(authenticationProvider)
            // Le filtre jwtAuthFilter est un filtre personnalisé qui valide les tokens d'authentification JWT. Le filtre jwtAuthFilter est ajouté avant le filtre UsernamePasswordAuthenticationFilter. Cela signifie que le filtre jwtAuthFilter est utilisé pour authentifier les utilisateurs qui ont déjà fourni un token d'authentification JWT.
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests((authorize) -> authorize
                // Sécurise la route pour les utilisateurs authentifiés
                .requestMatchers("/api/v1/current-user/**").authenticated()
                // Sécurise les routes commençant par "/api/v1/admin/" pour les utilisateurs avec le rôle ADMIN
                .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
                // Autorise toutes les autres requêtes sans nécessiter d'authentification.
                .anyRequest().permitAll()
            );

        return http.build();
    }

}
