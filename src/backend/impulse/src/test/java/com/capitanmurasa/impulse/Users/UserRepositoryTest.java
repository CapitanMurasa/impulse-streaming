package com.capitanmurasa.impulse.Users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.capitanmurasa.impulse.Users.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

@SpringBootTest
@Testcontainers
class UserRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres =
            new PostgreSQLContainer("postgres:17-alpine").withInitScript("init.sql");


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    void shouldSaveAndFindUser() {
        UsersModel testUserInit = userRepository.save(new UsersModel("test", "lol", "user", "test"));

        UsersModel testUserFind = userRepository.findByUsername("test");

        assertNotNull(testUserInit);
        assertEquals("test", testUserInit.getUserName());
        assertNotNull(testUserFind);
        assertEquals("test", testUserFind.getUserName());
    }
}