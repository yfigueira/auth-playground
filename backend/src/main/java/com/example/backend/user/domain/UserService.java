package com.example.backend.user.domain;

import com.example.backend.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User get(UUID id) {
        return repository.get(id).orElseThrow(
                () -> UserException.notFound(User.class, id)
        );
    }
}
