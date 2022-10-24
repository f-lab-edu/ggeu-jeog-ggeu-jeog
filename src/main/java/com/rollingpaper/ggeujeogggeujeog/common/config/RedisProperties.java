package com.rollingpaper.ggeujeogggeujeog.common.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.Getter;

@Getter
@ConfigurationProperties(prefix = "spring.redis")
@Configuration
@Profile({"local", "prod"})
public class RedisProperties {
	private String host;
	private int port;
	private RedisProperties master;
	private List<RedisProperties> slaves;
}
