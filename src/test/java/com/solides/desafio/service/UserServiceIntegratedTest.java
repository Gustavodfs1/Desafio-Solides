package com.solides.desafio.service;

import com.solides.desafio.GustavoApplication;
import com.solides.desafio.domain.User;
import com.solides.desafio.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static com.solides.desafio.fixture.UserFixture.givenUserDefault;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GustavoApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("test")
class UserServiceIntegratedTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
            .withDatabaseName("desafio")
            .withUsername("framework")
            .withPassword("pass");

    @BeforeEach
    void cleanUpUsers() {
        userRepository.deleteAll();
    }

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void givenDataWhenRegisterThenReturnToken() throws Exception {
        String register = """
                {
                	"username": "gustavo",
                	"email": "gustavo@gmail.com",
                	"password": "pass"
                }
                """;

        mockMvc.perform(post("/user/register")
                        .content(register)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isCreated()
                );

        assertEquals(1, userRepository.count());
    }

    @Test
    void givenDataWhenSignUpThenReturnToken() throws Exception {
        User user = givenUserDefault();
        String login = """
                {
                	"username": "gustavo",
                	"password": "%s"
                }
                """.formatted(user.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        mockMvc.perform(post("/user/login")
                        .content(login)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}