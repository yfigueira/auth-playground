package com.example.backend.security.auth.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRegistrationEntityRepository extends CrudRepository<UserRegistrationEntity, UUID> {
}
