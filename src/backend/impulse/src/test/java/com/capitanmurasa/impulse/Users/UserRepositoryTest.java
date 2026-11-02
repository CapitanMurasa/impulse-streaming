package com.capitanmurasa.impulse.Users;

import com.capitanmurasa.impulse.Users.UserRepository;
import com.capitanmurasa.impulse.Shared.EncryptManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
@Transactional
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
    void shouldSaveAndFindUser() {
        UsersModel testUserInit = userRepository.save(new UsersModel("test", "lol",
                "user", "test"));

        UsersModel testUserFind = userRepository.findByUsername("test");

        assertNotNull(testUserInit);
        assertEquals("test", testUserInit.getUserName());
        assertNotNull(testUserFind);
        assertEquals("test", testUserFind.getUserName());
    }

    @Test
    void checkPasswordHash(){
        EncryptManager encryptManager = new EncryptManager();

        UsersModel testUserHashInit = userRepository.save(new UsersModel("test",
                encryptManager.EncryptPassword("lol"), "user", "test"));

        UsersModel testUserHashFind = userRepository.findByUsername("test");

        assertNotNull(testUserHashInit);
        assertNotNull(testUserHashFind);
        assertNotNull(testUserHashFind.getPassword());
        assertTrue((encryptManager.DecryptPassword("lol", testUserHashFind.getPassword())));
    }

    @Test
    void checkWrongPasswordHash(){
        EncryptManager encryptManager = new EncryptManager();

        UsersModel testUserHashInit = userRepository.save(new UsersModel("test1",
                encryptManager.EncryptPassword("lol"), "user", "test"));

        UsersModel testUserHashFind = userRepository.findByUsername("test1");

        assertNotNull(testUserHashInit);
        assertNotNull(testUserHashFind);
        assertNotNull(testUserHashFind.getPassword());
        assertFalse((encryptManager.DecryptPassword("notlol", testUserHashFind.getPassword())));
    }

    @Test
    void shouldRejectDuplicateUsername(){
        userRepository.save(new UsersModel("testreal", "lol",
                "user", "test"));

        assertThrows(DataIntegrityViolationException.class, () ->{
            userRepository.save(new UsersModel("testreal", "lol",
                    "user", "test"));
        });
    }

}