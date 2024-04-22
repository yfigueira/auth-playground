package com.example.backend.user.web.dto;

import com.example.backend.user.domain.UserRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public record UserRegistrationDto(
        String firstName,
        String lastName,
        String email,
        String password,
        String repeatedPassword
) {
    @Mapper(componentModel = "spring")
    public interface UserRegistrationMapper {
        UserRegistration toDomain(UserRegistrationDto dto);
    }

    public static UserRegistrationMapper mapper() {
        return Mappers.getMapper(UserRegistrationDto.UserRegistrationMapper.class);
    }
}
