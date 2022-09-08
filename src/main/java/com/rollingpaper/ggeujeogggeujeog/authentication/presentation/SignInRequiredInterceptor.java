package com.rollingpaper.ggeujeogggeujeog.authentication.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.rollingpaper.ggeujeogggeujeog.common.constant.SessionConst;
import com.rollingpaper.ggeujeogggeujeog.user.exception.SignInRequiredException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SignInRequiredInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		HttpSession httpSession = request.getSession();

		if (httpSession == null || httpSession.getAttribute(SessionConst.USER_ID) == null) {
			log.info("Sign In required.");
			throw new SignInRequiredException();
		}

		return true;
	}
}
