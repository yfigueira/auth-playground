package com.example.backend.security.auth.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserAuthenticationEntityRepository extends CrudRepository<UserAuthenticationEntity, UUID> {
    UserAuthenticationEntity findByEmail(String email);
}
