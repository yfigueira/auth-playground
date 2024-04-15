package com.example.backend.user.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/public")
    public String publicGreeting() {
        return "Hello public user";
    }

    @GetMapping("/private")
    public String privateGreeting() {
        return "Hello authenticated user";
    }
}
