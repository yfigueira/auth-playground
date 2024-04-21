package com.example.backend.security;

import com.example.backend.user.domain.User;
import com.example.backend.user.domain.UserRepository;
import com.example.backend.user.persistence.UserEntityRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class AuthenticationIT {

    @LocalServerPort
    private Integer port;
    private final UserEntityRepository entityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private static final String BASE_PATH = "/api/auth";

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0-alpine");

    @Autowired
    public AuthenticationIT(UserEntityRepository repository, UserRepository userRepository, PasswordEncoder encoder) {
        this.entityRepository = repository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @AfterEach
    void tearDown() {
        entityRepository.deleteAll();
    }

    @Test
    void postgresContainerLoads() {
        assertThat(postgres.isCreated(), is(true));
        assertThat(postgres.isRunning(), is(true));
    }

    @Test
    void whenRegistered_AuthenticationShouldReturnTokenWithStatus200() {
        User user = User.builder()
                .firstName("John")
                .lastName("Smith")
                .email("jsmith@email.com")
                .password(encoder.encode("pass"))
                .build();

        userRepository.save(user);

        String requestBody = """
                {
                    "username": "jsmith@email.com",
                    "password": "pass"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("%s/%s".formatted(BASE_PATH, "login/username-password"))
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .body("size()", is(1));

    }
}
