package com.rollingpaper.ggeujeogggeujeog.authentication.application;

import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto.SignInRequestDto;
import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto.SignUpRequestDto;
import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto.VerifyEmailRequestDto;

public interface LoginService {
	void signIn(SignInRequestDto dto);
	void signOut();
	Long getUserId();
	void register(SignUpRequestDto dto);
	void confirmRegistration(VerifyEmailRequestDto dto);
}
