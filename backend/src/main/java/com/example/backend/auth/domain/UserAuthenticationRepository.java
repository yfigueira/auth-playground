package com.example.backend.auth.domain;

import java.util.Optional;

public interface UserAuthenticationRepository {

    Optional<UserAuthentication> findByUsername(String username);
}
