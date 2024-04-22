package com.example.backend.user.web;

import com.example.backend.user.domain.UserService;
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
}
