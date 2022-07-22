package com.rollingpaper.ggeujeogggeujeog.user.presentation.dto;

import com.rollingpaper.ggeujeogggeujeog.user.domain.Role;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserProfileResponseDto {

	private Long userId;
	private String nickname;
	private Role role;

	@Builder
	public UserProfileResponseDto(Long userId, String nickname, Role role) {
		this.userId = userId;
		this.nickname = nickname;
		this.role = role;
	}

	public static UserProfileResponseDto from(User user) {
		return UserProfileResponseDto.builder()
			.userId(user.getId())
			.nickname(user.getNickname())
			.role(user.getRole())
			.build();
	}
}
