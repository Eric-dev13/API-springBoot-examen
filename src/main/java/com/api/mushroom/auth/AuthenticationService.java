package com.api.mushroom.auth;


import com.api.mushroom.configuration.JwtService;
import com.api.mushroom.entity.UserEntity;
import com.api.mushroom.repository.UserEntityJpaRepository;
import com.api.mushroom.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserEntityJpaRepository userEntityJpaRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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
        var user = userEntityJpaRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
