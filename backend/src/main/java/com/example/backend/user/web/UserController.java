package com.example.backend.user.web;

import com.example.backend.user.domain.UserService;
import com.example.backend.user.web.dto.UserRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createUser(@RequestBody UserRegistrationDto dto) {
        var registration = UserRegistrationDto.mapper().toDomain(dto);
        userService.create(registration);
        return ResponseEntity.ok().build();
    }
}
