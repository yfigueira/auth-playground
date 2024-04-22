package com.example.backend.user.persistence;

import com.example.backend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDatabaseRepository implements UserRepository {

    private final UserEntityRepository entityRepository;

    private final UserMapper mapper;

}
