package com.rollingpaper.ggeujeogggeujeog.authentication.infrastructure;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.authentication.domain.TokenRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RedisTokenRepository implements TokenRepository {

	private final RedisTemplate<String, String> redisTemplate;

	@Override
	public void storeToken(String userEmail, String token) {
		redisTemplate.opsForValue().set(
			userEmail,
			token,
			10,
			TimeUnit.MINUTES
		);
	}
	@Override
	public String getToken(String email) {
		return Optional.ofNullable(redisTemplate.opsForValue().get(email))
			.orElseThrow(() -> new IllegalArgumentException("토큰 값이 존재하지 않습니다."));
	}
}
