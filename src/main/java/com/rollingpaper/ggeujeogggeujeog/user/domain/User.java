package com.rollingpaper.ggeujeogggeujeog.user.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User implements Serializable {
	private Long id;
	private String email;
	private String password;
	private boolean verified;
	private Role role;
	private String nickname;
	private String accessToken;
	private String refreshToken;

	@Builder
	public User(Long id, String email, String password, boolean verified,
		Role role, String nickname, String accessToken, String refreshToken) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.verified = verified;
		this.role = role;
		this.nickname = nickname;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public void updateProfile(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
	}

	public void verifiedUser() {
		this.verified = true;
	}
}