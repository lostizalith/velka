package com.github.lostizalith.velka;

import org.junit.ClassRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(classes = VelkaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    private static final Integer ORIGINAL_PORT = 5432;

    @ClassRule
    public static GenericContainer POSTGRES = new GenericContainer("postgres:9.5-alpine")
        .withExposedPorts(ORIGINAL_PORT)
        .withEnv("POSTGRES_DB", "velka")
        .withEnv("POSTGRES_USER", "velka_user")
        .withEnv("POSTGRES_PASSWORD", "testpwd");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(final ConfigurableApplicationContext configurableApplicationContext) {
            final String ipAddress = POSTGRES.getContainerIpAddress();
            final Integer port = POSTGRES.getMappedPort(ORIGINAL_PORT);

            final TestPropertyValues values = TestPropertyValues.of(
                "datasource.host=" + ipAddress + ":" + port
            );

            values.applyTo(configurableApplicationContext);
        }
    }
}
