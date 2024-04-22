package com.example.backend.security;

import com.example.backend.security.auth.domain.UserRegistration;
import com.example.backend.security.auth.domain.AuthenticationRepository;
import com.example.backend.security.auth.persistence.UserRegistrationEntityRepository;
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
    private final UserRegistrationEntityRepository registrationRepository;
    private final AuthenticationRepository authenticationRepository;
    private final PasswordEncoder encoder;
    private static final String BASE_PATH = "/api/auth";

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0-alpine");

    @Autowired
    public AuthenticationIT(UserRegistrationEntityRepository registrationRepository, AuthenticationRepository authenticationRepository, PasswordEncoder encoder) {
        this.registrationRepository = registrationRepository;
        this.authenticationRepository = authenticationRepository;
        this.encoder = encoder;
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @AfterEach
    void tearDown() {
        registrationRepository.deleteAll();
    }

    @Test
    void postgresContainerLoads() {
        assertThat(postgres.isCreated(), is(true));
        assertThat(postgres.isRunning(), is(true));
    }

    @Test
    void whenNotRegistered_RegistrationShouldReturnStatus200() {
        String registration = """
                    {
                        "firstName": "John",
                        "lastName": "Smith",
                        "email": "jsmith@email.com",
                        "password": "password",
                        "repeatedPassword": "password"
                    }
                """;

        given()
                .contentType(ContentType.JSON)
                .and()
                .body(registration)
                .when()
                .post("%s/register".formatted(BASE_PATH))
                .then()
                .statusCode(200);
    }

    @Test
    void whenPasswordsDoNotMatch_RegistrationShouldReturnPasswordMismatchErrorMessageWithStatus400() {
        String registration = """
                    {
                        "firstName": "John",
                        "lastName": "Smith",
                        "email": "jsmith@email.com",
                        "password": "password",
                        "repeatedPassword": "other-password"
                    }
                """;

        given()
                .contentType(ContentType.JSON)
                .and()
                .body(registration)
                .when()
                .post("%s/register".formatted(BASE_PATH))
                .then()
                .statusCode(400)
                .body("size()", is(3))
                .body("status", notNullValue())
                .body("causedBy", notNullValue())
                .body("timestamp", notNullValue());
    }

    @Test
    void whenRegistered_AuthenticationShouldReturnTokenWithStatus200() {
        populateDatabase();

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
                .post("%s/login/username-password".formatted(BASE_PATH))
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .body("size()", is(1));

    }

    @Test
    void whenNotRegistered_AuthenticationShouldReturnUnauthorizedErrorMessageWithStatus401() {
        String requestBody = """
                {
                    "username": "-",
                    "password": "-"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("%s/%s".formatted(BASE_PATH, "login/username-password"))
                .then()
                .statusCode(401)
                .body("size()", is(3))
                .body("status", notNullValue())
                .body("causedBy", notNullValue())
                .body("timestamp", notNullValue());
    }

    @Test
    void whenProvidingWrongPassword_AuthenticationShouldReturnUnauthorizedErrorMessageWithStatus401() {
        populateDatabase();

        String requestBody = """
                {
                    "username": "jsmith@email.com",
                    "password": "wrong-password"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("%s/%s".formatted(BASE_PATH, "login/username-password"))
                .then()
                .statusCode(401)
                .body("size()", is(3))
                .body("status", notNullValue())
                .body("causedBy", notNullValue())
                .body("timestamp", notNullValue());
    }

    private void populateDatabase() {
        UserRegistration userRegistration = UserRegistration.builder()
                .firstName("John")
                .lastName("Smith")
                .email("jsmith@email.com")
                .password(encoder.encode("pass"))
                .build();

        authenticationRepository.save(userRegistration);
    }
}
