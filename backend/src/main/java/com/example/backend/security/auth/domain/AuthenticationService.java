package com.example.backend.security.auth.domain;

import com.example.backend.security.auth.exception.AuthenticationException;
import com.example.backend.security.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final AuthenticationRepository repository;

    private final JwtService jwtService;

    private final PasswordEncoder encoder;

    public LoginResponse authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                ));

        var token = jwtService.generateToken(authentication);
        return new LoginResponse(token);
    }

    public void create(RegistrationRequest request) {
        if (!request.password().equals(request.repeatedPassword())) {
            throw AuthenticationException.passwordMismatch();
        }

        var registration = UserRegistration.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(encoder.encode(request.password()))
                .build();

        repository.save(registration);
    }
}
