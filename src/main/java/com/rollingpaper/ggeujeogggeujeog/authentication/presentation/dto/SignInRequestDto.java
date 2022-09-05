package com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public class SignInRequestDto {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,16}",
		message = "대소문자, 숫자, 특수문자 하나 이상 최소 8자 최대 16자로 입력해주세요."
	)
	private String password;

	public SignInRequestDto(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
