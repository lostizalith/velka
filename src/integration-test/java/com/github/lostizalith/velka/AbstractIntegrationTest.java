package com.uber.marketing.application;

import com.github.lostizalith.velka.VelkaApplication;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.GenericContainer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VelkaApplication.class)
//@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public abstract class AbstractIntegrationTest {

//    private static final Integer ORIGINAL_PORT = 5432;

//    @ClassRule
//    public static final GenericContainer POSTGRES = new NonStoppingGenericContainer("postgres:9.5-alpine")
//        .withExposedPorts(ORIGINAL_PORT)
//        .withEnv("POSTGRES_DB", "velka")
//        .withEnv("POSTGRES_USER", "velka_user")
//        .withEnv("POSTGRES_PASSWORD", "testpwd");

//    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//        @Override
//        public void initialize(final ConfigurableApplicationContext configurableApplicationContext) {
//            final String ipAddress = POSTGRES.getContainerIpAddress();
//            final Integer port = POSTGRES.getMappedPort(ORIGINAL_PORT);
//
//            TestPropertyValues.of("testcontainers", configurableApplicationContext.getEnvironment(),
//                "spring.datasource.url=jdbc:mysql://" + ipAddress + ":" + port + "/lol?autoReconnect=true&useSSL=false",
//                "spring.datasource.username=_user",
//                "spring.datasource.password=_pass"
//            );
//        }
//    }

    /**
     * Non stopping generic container implementation.
     */
//    public static class NonStoppingGenericContainer extends GenericContainer implements Container, AutoCloseable {
//
//        public NonStoppingGenericContainer(final String dockerImageName) {
//            super(dockerImageName);
//        }
//
//        @Override
//        public void start() {
//            // One container to rule them all
//            if (containerId == null) {
//                super.start();
//            }
//        }
//
//        @Override
//        public void stop() {
//            // do not stop container after it is executed
//        }
//    }
}
