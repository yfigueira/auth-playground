package com.example.backend.security.auth.web.dto;

import com.example.backend.security.auth.domain.LoginRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public record LoginRequestDto(
        String username,
        String password
) {
    @Mapper(componentModel = "spring")
    public interface LoginRequestMapper {
        LoginRequest toDomain(LoginRequestDto dto);
    }

    public static LoginRequestMapper mapper() {
        return Mappers.getMapper(LoginRequestDto.LoginRequestMapper.class);
    }
}
