package com.rollingpaper.ggeujeogggeujeog.authentication.application;

import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto.SignInRequestDto;

public interface LoginService {

	void signIn(SignInRequestDto dto);

	void signOut();

	Long getUserId();
}
