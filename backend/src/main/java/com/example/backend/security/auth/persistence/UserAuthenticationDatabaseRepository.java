package com.example.backend.security.auth.persistence;

import com.example.backend.security.auth.domain.UserAuthentication;
import com.example.backend.security.auth.domain.UserAuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserAuthenticationDatabaseRepository implements UserAuthenticationRepository {

    private final UserAuthenticationEntityRepository entityRepository;

    private final UserAuthenticationMapper mapper;

    @Override
    public Optional<UserAuthentication> findByUsername(String username) {
        return Optional.ofNullable(entityRepository.findByEmail(username))
                .map(mapper::toDomain);
    }
}
