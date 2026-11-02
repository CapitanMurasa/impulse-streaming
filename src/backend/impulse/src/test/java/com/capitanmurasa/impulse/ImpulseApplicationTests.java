package com.capitanmurasa.impulse;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

@SpringBootTest
@Testcontainers
class ImpulseApplicationTests {

	@Container
	@ServiceConnection
	static PostgreSQLContainer postgres =
			new PostgreSQLContainer("postgres:17-alpine").withInitScript("init.sql");



	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {

	}

	@Test
	void connectionTest(){
		int query = jdbcTemplate.queryForObject("SELECT 1", Integer.class);

		assertEquals(1, query);
	}


}
