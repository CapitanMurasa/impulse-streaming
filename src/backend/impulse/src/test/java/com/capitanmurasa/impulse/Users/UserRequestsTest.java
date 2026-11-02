package com.capitanmurasa.impulse.Users;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class UserRequestsTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres =
            new PostgreSQLContainer("postgres:17-alpine").withInitScript("init.sql");

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnIndex() throws Exception {
        mockMvc.perform(get("/api/v1/"))
                .andExpect(status().isOk());
    }

    @Test
    void registerLoginCycleTest() throws Exception {
        mockMvc.perform(get("/api/v1/register/murasa?password=test1"))
                .andExpect(status().is(201));
        MvcResult result = mockMvc.perform(get("/api/v1/login/murasa?password=test1"))
                .andExpect(status().is(200)).andReturn();
        MockHttpSession session =
                (MockHttpSession) result.getRequest().getSession(false);
        assertNotNull(session);
        mockMvc.perform(
                get("/api/v1/userpage").session(session)
        ).andExpect(jsonPath("$.name").value("murasa"));
        mockMvc.perform(
                get("/api/v1/logout").session(session)
        );
    }

}
