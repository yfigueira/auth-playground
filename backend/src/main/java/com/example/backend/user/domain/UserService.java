package com.example.backend.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    public User create(UserRegistration userRegistration) {
        var user = User.builder()
                .firstName(userRegistration.firstName())
                .lastName(userRegistration.lastName())
                .email(userRegistration.email())
                .password(encoder.encode(userRegistration.password()))
                .build();

        return repository.save(user);
    }
}
