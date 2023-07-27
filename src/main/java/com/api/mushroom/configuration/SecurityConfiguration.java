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
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeHttpRequests()
                //.requestMatchers("/api/v1/user/**").hasRole("USER")
                //.requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                //.anyRequest().authenticate()
                .anyRequest().permitAll()
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
