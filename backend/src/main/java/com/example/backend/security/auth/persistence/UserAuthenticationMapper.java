package com.example.backend.security.auth.persistence;

import com.example.backend.security.auth.domain.UserAuthentication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserAuthenticationMapper {

    @Mapping(source = "email", target = "username")
    UserAuthentication toDomain(UserAuthenticationEntity entity);
}