package com.rollingpaper.ggeujeogggeujeog.common.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.rollingpaper.ggeujeogggeujeog.common.util.PasswordEncoder;

@TestConfiguration
public class PasswordEncoderTestConfig {

	class DefaultPasswordEncoder implements PasswordEncoder {

		@Override
		public String encode(CharSequence password) {
			return password.toString();
		}

		@Override
		public boolean matches(CharSequence rawPassword, String encodedPassword) {
			return rawPassword.equals(encodedPassword);
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new DefaultPasswordEncoder();
	}

}
