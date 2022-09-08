package com.rollingpaper.ggeujeogggeujeog.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class NcpImageStorageConfig {

	@Value("${ncp.storage.end-point}")
	private String endPoint;

	@Value("${ncp.storage.region}")
	private String region;

	@Value("${ncp.storage.access-key}")
	private String accessKey;

	@Value("${ncp.storage.secret-key}")
	private String secretKey;

	@Bean
	public AmazonS3 amazonS3() {
		return AmazonS3ClientBuilder
			.standard()
			.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, region))
			.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
			.build();
	}
}
