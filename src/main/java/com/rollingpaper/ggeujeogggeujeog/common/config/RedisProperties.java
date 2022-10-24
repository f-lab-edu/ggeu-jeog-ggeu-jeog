package com.rollingpaper.ggeujeogggeujeog.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@ConfigurationProperties(prefix = "spring.redis")
@Configuration
@Profile({"local", "prod"})
public class RedisProperties {

	private String host;
	private int port;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
