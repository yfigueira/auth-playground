package com.example.backend.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    public User create(UserRegistration registration) {
        if (!registration.password()
                .equals(registration.repeatedPassword())) {
            throw new RuntimeException();
        }

        var user = User.builder()
                .firstName(registration.firstName())
                .lastName(registration.lastName())
                .email(registration.email())
                .password(encoder.encode(registration.password()))
                .build();

        return repository.save(user);
    }
}
