package com.example.backend.user.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserEntityRepository extends CrudRepository<UserEntity, UUID> {

}
