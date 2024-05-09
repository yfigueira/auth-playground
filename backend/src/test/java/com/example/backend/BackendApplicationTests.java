package com.example.backend;

import com.example.backend.security.auth.web.AuthenticationController;
import com.example.backend.user.persistence.UserEntityRepository;
import io.restassured.RestAssured;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class BackendApplicationTests {

    @LocalServerPort
    private Integer port;

    private final UserEntityRepository repository;

    private final AuthenticationController authenticationController;

    @Autowired
    public BackendApplicationTests(UserEntityRepository repository, AuthenticationController authenticationController) {
        this.repository = repository;
        this.authenticationController = authenticationController;
    }

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0-alpine");

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void postgresContainerLoads() {
        assertThat(postgres.isCreated(), is(true));
        assertThat(postgres.isRunning(), is(true));
    }

}
