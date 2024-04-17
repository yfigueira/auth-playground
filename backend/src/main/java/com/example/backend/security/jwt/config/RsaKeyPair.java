package com.example.backend.security.jwt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record RsaKeyPair(
        RSAPublicKey publicKey,
        RSAPrivateKey privateKey
) {
}
