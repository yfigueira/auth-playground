package com.example.backend.user.web;

import com.example.backend.user.domain.UserService;
import com.example.backend.user.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto get(@PathVariable UUID id) {
        var user = userService.get(id);
        return UserDto.mapper().toDto(user);
    }
}
