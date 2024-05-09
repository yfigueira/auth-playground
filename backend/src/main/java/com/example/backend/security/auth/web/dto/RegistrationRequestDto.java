package com.example.backend.security.auth.web.dto;

import com.example.backend.security.auth.domain.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public record RegistrationRequestDto(
        String firstName,
        String lastName,
        String email,
        String password,
        String repeatedPassword
) {
    @Mapper(componentModel = "spring")
    public interface RegistrationRequestMapper {
        RegistrationRequest toDomain(RegistrationRequestDto dto);
    }

    public static RegistrationRequestMapper mapper() {
        return Mappers.getMapper(RegistrationRequestDto.RegistrationRequestMapper.class);
    }
}
