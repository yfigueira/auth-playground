package com.example.backend.user.web;

import com.example.backend.user.domain.User;
import com.example.backend.user.domain.UserRegistration;
import com.example.backend.user.domain.UserService;
import com.example.backend.user.web.dto.UserRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/public")
    public String publicGreeting() {
        return "Hello public user";
    }

    @GetMapping("/private")
    public String privateGreeting() {
        return "Hello authenticated user";
    }

    @PostMapping
    public User createUser(@RequestBody UserRegistrationDto dto) {
        var user = UserRegistration.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .password(dto.password())
                .build();

        return userService.create(user);
    }
}
