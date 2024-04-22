package com.example.backend.security.auth.web.dto;

import com.example.backend.security.auth.domain.LoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public record LoginResponseDto(
        String token
) {

    @Mapper(componentModel = "spring")
    public interface LoginResponseMapper {
        LoginResponseDto toDto(LoginResponse domain);
    }

    public static LoginResponseMapper mapper() {
        return Mappers.getMapper(LoginResponseDto.LoginResponseMapper.class);
    }
}
