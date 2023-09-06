package com.api.mushroom.auth;


import com.api.mushroom.configuration.JwtService;
import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.repository.UserEntityJpaRepository;
import com.api.mushroom.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserEntityJpaRepository userEntityJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService; // ++

    public AuthenticationResponse register(RegisterRequest request) {
       var user = UserEntity.builder()
               .pseudo(request.getPseudo())
               .role(Role.USER)
               .lastname(request.getLastname())
               .firstname(request.getFirstname())
               .email(request.getEmail())
               .password(passwordEncoder.encode(request.getPassword()))
               .filename(request.getAvatar())
               .isVerified(false)
               .build();
        userEntityJpaRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
        );
        var user = userEntityJpaRepository.findByEmail(request.getEmail()) .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        Map<String, Object> userDetailsMap = new HashMap<>();
        userDetailsMap.put("username", user.getUsername());
        userDetailsMap.put("pseudo",  user.getPseudo());
        userDetailsMap.put("roles",  user.getAuthorities());
        userDetailsMap.put("firstname",  user.getFirstname());
        userDetailsMap.put("lastname",  user.getFirstname());
        userDetailsMap.put("filename",  user.getFilename());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(userDetailsMap)// ++
                .build();
    }
}
