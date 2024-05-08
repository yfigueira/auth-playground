package com.example.backend.user.web.dto;

import com.example.backend.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public record UserDto(
        String firstName,
        String lastName,
        String email
) {
    @Mapper(componentModel = "spring")
    public interface UserDtoMapper {
        UserDto toDto(User domain);
    }

    public static UserDtoMapper mapper() {
        return Mappers.getMapper(UserDto.UserDtoMapper.class);
    }
}
