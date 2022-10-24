package com.rollingpaper.ggeujeogggeujeog.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import io.lettuce.core.ReadFrom;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableRedisHttpSession
@Profile({"local", "prod"})
public class RedisConfig {

	private final RedisProperties properties;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
			.readFrom(ReadFrom.REPLICA_PREFERRED)
			.build();
		RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration(properties.getHost(),
			properties.getPort());
		return new LettuceConnectionFactory(standaloneConfiguration, clientConfig);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
		return redisTemplate;
	}
}
