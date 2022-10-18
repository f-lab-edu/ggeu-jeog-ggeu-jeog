package com.rollingpaper.ggeujeogggeujeog.common.config;

import java.io.File;
import java.time.Duration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(
	classes = {
		PasswordEncoderTestConfig.class,
		MessageSenderTestConfig.class,
		LocalStackS3TestConfig.class
	}
)
@ActiveProfiles("test")
@Testcontainers
public abstract class AbstractContainerBaseTest {

	static final DockerComposeContainer DOCKER_COMPOSE_CONTAINER;

	static {
		DOCKER_COMPOSE_CONTAINER = new DockerComposeContainer(
			new File("docker-compose-test.yml"))
			.withExposedService("db_1",
			3306,
			Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)))
			.withExposedService("session_1",
				6379,
				Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)));
		DOCKER_COMPOSE_CONTAINER.start();
	}
}
