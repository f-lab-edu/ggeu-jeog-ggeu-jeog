package com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class VerifyEmailRequestDto {

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String token;

	public VerifyEmailRequestDto(String email, String token) {
		this.email = email;
		this.token = token;
	}
}
