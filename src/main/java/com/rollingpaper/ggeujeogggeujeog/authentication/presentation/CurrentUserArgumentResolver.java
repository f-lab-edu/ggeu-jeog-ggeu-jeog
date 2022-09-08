package com.rollingpaper.ggeujeogggeujeog.authentication.presentation;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.rollingpaper.ggeujeogggeujeog.authentication.application.LoginService;
import com.rollingpaper.ggeujeogggeujeog.user.application.UserService;
import com.rollingpaper.ggeujeogggeujeog.user.exception.NoSuchUserException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

	private final LoginService loginService;
	private final UserService userService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(CurrentUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory
	) throws Exception {
		return userService.getUserById(loginService.getUserId())
			.orElseThrow(NoSuchUserException::new);
	}
}
