package com.example.backend.user.config;

import com.example.backend.user.domain.UserRepository;
import com.example.backend.user.domain.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaRepositories("com.example.backend.user.persistence")
public class UserConfig {

    @Bean
    public UserService userService(UserRepository repository, PasswordEncoder encoder) {
        return new UserService(repository, encoder);
    }
}
