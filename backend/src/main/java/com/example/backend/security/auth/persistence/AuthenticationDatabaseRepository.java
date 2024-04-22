package com.example.backend.security.auth.persistence;

import com.example.backend.security.auth.domain.UserRegistration;
import com.example.backend.security.auth.domain.UserAuthentication;
import com.example.backend.security.auth.domain.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthenticationDatabaseRepository implements AuthenticationRepository {

    private final UserAuthenticationEntityRepository authenticationEntityRepository;

    private final UserRegistrationEntityRepository registrationEntityRepository;

    private final AuthenticationMapper mapper;

    @Override
    public Optional<UserAuthentication> findByUsername(String username) {
        return Optional.ofNullable(authenticationEntityRepository.findByEmail(username))
                .map(mapper::toDomain);
    }

    @Override
    public void save(UserRegistration userRegistration) {
        registrationEntityRepository.save(mapper.toEntity(userRegistration));
    }
}
