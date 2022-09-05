package com.rollingpaper.ggeujeogggeujeog.common.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rollingpaper.ggeujeogggeujeog.authentication.application.LoginService;
import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.CurrentUserArgumentResolver;
import com.rollingpaper.ggeujeogggeujeog.user.application.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Profile("local")
@Configuration
public class WebLocalConfig implements WebMvcConfigurer {

	private final UserService userService;
	private final LoginService loginService;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new CurrentUserArgumentResolver(loginService, userService));
	}
}
