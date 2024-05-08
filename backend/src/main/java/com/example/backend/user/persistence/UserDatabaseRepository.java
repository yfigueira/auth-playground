package com.example.backend.user.persistence;

import com.example.backend.user.domain.User;
import com.example.backend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserDatabaseRepository implements UserRepository {

    private final UserEntityRepository entityRepository;

    private final UserMapper mapper;

    @Override
    public Optional<User> get(UUID id) {
        return entityRepository.findById(id)
                .map(mapper::toDomain);
    }
}
