package com.rollingpaper.ggeujeogggeujeog.common.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rollingpaper.ggeujeogggeujeog.authentication.application.LoginService;
import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.CurrentUserArgumentResolver;
import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.SignInRequiredInterceptor;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.MessageSender;
import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.FirebaseMessageSender;
import com.rollingpaper.ggeujeogggeujeog.user.application.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Profile("prod")
@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final UserService userService;
	private final LoginService loginService;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SignInRequiredInterceptor())
			.excludePathPatterns(
				"/api/v1/users/sign-up", "/api/v1/users/sign-in", "/api/v1/users/sign-out",
				"/api/v1/boards/open", "/api/v1/boards/search"
			);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new CurrentUserArgumentResolver(loginService, userService));
	}

	@Bean
	public MessageSender messageSender() {
		return new FirebaseMessageSender();
	}
}
