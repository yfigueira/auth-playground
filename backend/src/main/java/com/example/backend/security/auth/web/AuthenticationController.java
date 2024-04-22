package com.example.backend.security.auth.web;

import com.example.backend.security.auth.domain.AuthenticationService;
import com.example.backend.security.auth.web.dto.LoginRequestDto;
import com.example.backend.security.auth.web.dto.LoginResponseDto;
import com.example.backend.security.auth.web.dto.RegistrationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public LoginResponseDto loginUsernamePassword(@RequestBody LoginRequestDto dto) {
        var request = LoginRequestDto.mapper().toDomain(dto);
        var response = service.authenticate(request);
        return LoginResponseDto.mapper().toDto(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequestDto dto) {
        var registration = RegistrationRequestDto.mapper().toDomain(dto);
        service.create(registration);
        return ResponseEntity.ok().build();
    }
}
