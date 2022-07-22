package com.rollingpaper.ggeujeogggeujeog.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.rollingpaper.ggeujeogggeujeog.common.util.BcryptPasswordEncoder;
import com.rollingpaper.ggeujeogggeujeog.common.util.PasswordEncoder;

@Component
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BcryptPasswordEncoder();
	}
}
