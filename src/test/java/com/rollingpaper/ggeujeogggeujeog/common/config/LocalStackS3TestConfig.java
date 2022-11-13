package com.rollingpaper.ggeujeogggeujeog.common.config;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@TestConfiguration
public class LocalStackS3TestConfig {

	@Value("${ncp.storage.bucket}")
	private String bucket;

	private static final DockerImageName LOCAL_STACK_IMAGE = DockerImageName.parse("localstack/localstack");

	@Bean(initMethod = "start", destroyMethod = "stop")
	public LocalStackContainer localStackContainer() {
		return new LocalStackContainer(LOCAL_STACK_IMAGE)
			.withServices(S3);
	}

	@Bean
	public AmazonS3 amazonS3(LocalStackContainer localStackContainer) {
		AmazonS3 amazonS3 = AmazonS3ClientBuilder
			.standard()
			.withEndpointConfiguration(localStackContainer.getEndpointConfiguration(S3))
			.withCredentials(localStackContainer.getDefaultCredentialsProvider())
			.build();
		amazonS3.createBucket(bucket);
		return amazonS3;
	}
}
