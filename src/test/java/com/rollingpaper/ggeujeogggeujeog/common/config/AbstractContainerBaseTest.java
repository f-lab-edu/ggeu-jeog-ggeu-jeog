package com.rollingpaper.ggeujeogggeujeog.common.config;

import java.io.File;
import java.time.Duration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(
	classes = {
		PasswordEncoderTestConfig.class,
		MessageSenderTestConfig.class,
		LocalStackS3TestConfig.class,
		RedisContainerTestConfig.class
	}
)
@ActiveProfiles("test")
@Testcontainers
public abstract class AbstractContainerBaseTest {

	static final int MYSQL_DEFAULT_PORT = 3306;
	static final int REDIS_DEFAULT_PORT = 6379;
	static final String DB_SCHEMA = "mydb";

	static DockerComposeContainer dockerComposeContainer;

	static {
		dockerComposeContainer =
			new DockerComposeContainer(new File("docker-compose-test.yml"))
				.withExposedService("session_1", REDIS_DEFAULT_PORT,
					Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)))
				.withExposedService("db_1", MYSQL_DEFAULT_PORT,
					Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)));

		dockerComposeContainer.start();
	}


	@DynamicPropertySource
	public static void overrideProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.session.redis.master.host",
			() -> dockerComposeContainer.getServiceHost("session_1", REDIS_DEFAULT_PORT));
		registry.add("spring.session.redis.master.port",
			() -> dockerComposeContainer.getServicePort("session_1", REDIS_DEFAULT_PORT));

		registry.add("spring.datasource.url", () ->
			"jdbc:mysql://" +
				dockerComposeContainer.getServiceHost("db_1", MYSQL_DEFAULT_PORT) +
				":" +
				dockerComposeContainer.getServicePort("db_1", MYSQL_DEFAULT_PORT) +
				"/" + DB_SCHEMA);
	}
}
