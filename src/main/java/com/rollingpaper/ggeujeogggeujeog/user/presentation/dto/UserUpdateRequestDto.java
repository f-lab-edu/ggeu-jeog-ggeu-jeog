package com.rollingpaper.ggeujeogggeujeog.user.presentation.dto;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {

	@NotBlank
	@Pattern(
		regexp = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{1,15}$",
		message = "특수문자를 제외한 문자, 숫자 1가지 이상 최소 1자 최대 15자로 입력해주세요."
	)
	private String nickname;

	@NotBlank
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,16}",
		message = "대소문자, 숫자, 특수문자 하나 이상 최소 8자 최대 16자로 입력해주세요."
	)
	private String password;

	public UserUpdateRequestDto(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
