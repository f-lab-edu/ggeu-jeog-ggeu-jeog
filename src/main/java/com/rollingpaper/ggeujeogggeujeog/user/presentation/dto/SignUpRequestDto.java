package com.rollingpaper.ggeujeogggeujeog.user.presentation.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,16}",
		message = "대소문자, 숫자, 특수문자 하나 이상 최소 8자 최대 16자로 입력해주세요."
	)
	private String password;

	@NotBlank
	@Pattern(
		regexp = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{1,15}$",
		message = "특수문자를 제외한 문자, 숫자 1가지 이상 최소 1자 최대 15자로 입력해주세요."
	)
	private String nickname;

	@Builder
	public SignUpRequestDto(String email, String password, String nickname) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static User toEntity(SignUpRequestDto dto) {
		return User.builder()
			.email(dto.getEmail())
			.password(dto.getPassword())
			.nickname(dto.getNickname())
			.build();
	}
}
