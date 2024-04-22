package com.example.backend.security.auth.domain;

import java.util.Optional;

public interface AuthenticationRepository {

    Optional<UserAuthentication> findByUsername(String username);

    void save(UserRegistration userRegistration);
}
