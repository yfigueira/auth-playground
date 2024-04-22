package com.example.backend.user;

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
public class UserIT {

    @LocalServerPort
    private Integer port;
    private final UserEntityRepository entityRepository;
    private static final String BASE_PATH = "/api/users";

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0-alpine");

    @Autowired
    public UserIT(UserEntityRepository entityRepository) {
        this.entityRepository = entityRepository;
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
                .post(BASE_PATH)
                .then()
                .statusCode(200);
    }

    @Test
    void whenPasswordsDoNotMatch_RegistrationShouldReturnPasswordMismatchErrorMessageWithStatus409() {
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
                .post(BASE_PATH)
                .then()
                .statusCode(409)
                .body("size()", is(3))
                .body("status", notNullValue())
                .body("causedBy", notNullValue())
                .body("timestamp", notNullValue());
    }
}
