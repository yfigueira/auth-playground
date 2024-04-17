package com.example.backend.security.auth.web;

import com.example.backend.security.auth.domain.AuthenticationService;
import com.example.backend.security.auth.web.dto.LoginRequest;
import com.example.backend.security.auth.web.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login/username-password")
    public LoginResponse loginUsernamePassword(@RequestBody LoginRequest request) {
        var response = service.authenticate(request);
        return new LoginResponse(response);
    }
}
