package com.api.mushroom.configuration;

import com.api.mushroom.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
            //  Définit la politique de création de sessions comme étant sans état (stateless), ce qui est courant pour les API REST utilisant des tokens d'authentification.
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // Configure le fournisseur d'authentification que vous avez défini ailleurs dans votre code.
            .authenticationProvider(authenticationProvider)
            // Ajoute un filtre personnalisé (jwtAuthFilter) avant le filtre UsernamePasswordAuthenticationFilter, qui gère l'authentification basée sur les noms d'utilisateur et les mots de passe.
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(a -> {
                // Sécurise la route pour les utilisateurs authentifiés
                //.requestMatchers("/api/v1/admin/**").authenticated()
                // Sécurise la route pour les utilisateurs avec le rôle ADMIN
                a.requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN");
                // Sécurise la route pour les utilisateurs avec le rôle USER
                a.requestMatchers("/api/v1/user/**").hasAuthority("USER");
                // Autorise toutes les autres requêtes sans nécessiter d'authentification.
                a.anyRequest().permitAll();
            });

        return http.build();
    }

}
