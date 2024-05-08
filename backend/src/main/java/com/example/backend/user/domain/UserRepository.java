package com.example.backend.user.domain;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> get(UUID id);
}
