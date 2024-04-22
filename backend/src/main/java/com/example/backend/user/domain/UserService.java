package com.example.backend.user.domain;

import com.example.backend.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    public void create(UserRegistration registration) {
        if (!registration.password().equals(registration.repeatedPassword())) {
            throw UserException.passwordMismatch();
        }

        var user = User.builder()
                .firstName(registration.firstName())
                .lastName(registration.lastName())
                .email(registration.email())
                .password(encoder.encode(registration.password()))
                .build();

        repository.save(user);
    }
}
