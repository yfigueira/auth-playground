package com.example.backend.security.auth.persistence;

import com.example.backend.security.auth.domain.UserRegistration;
import com.example.backend.security.auth.domain.UserAuthentication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {

    @Mapping(source = "email", target = "username")
    UserAuthentication toDomain(UserAuthenticationEntity entity);

    UserRegistrationEntity toEntity(UserRegistration domain);
}
