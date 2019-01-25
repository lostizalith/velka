package com.github.lostizalith.velka.account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountServiceTest {

    private static final Integer ORIGINAL_PORT = 5432;

    private GenericContainer postgresql = new GenericContainer("postgres:9.5-alpine")
        .withExposedPorts(ORIGINAL_PORT)
        .withEnv("POSTGRES_DB", "velka")
        .withEnv("POSTGRES_USER", "velka_user")
        .withEnv("POSTGRES_PASSWORD", "testpwd");

    @Before
    public void setUp() {
        postgresql.start();
    }

    @After
    public void after() {
        postgresql.stop();
    }

    @Test
    public void test() {
        assertTrue(postgresql.isRunning());
    }
}
