package com.rollingpaper.ggeujeogggeujeog.user.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {
	private Long id;
	private String email;
	private String password;
	private boolean verified;
	private Role role;
	private String nickname;
	private String accessToken;
	private String refreshToken;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	@Builder
	public User(Long id, String email, String password, boolean verified,
		Role role, String nickname, String accessToken, String refreshToken, LocalDateTime createdDate,
		LocalDateTime updatedDate) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.verified = verified;
		this.role = role;
		this.nickname = nickname;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
}