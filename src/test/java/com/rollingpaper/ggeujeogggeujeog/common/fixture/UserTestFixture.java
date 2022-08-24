package com.rollingpaper.ggeujeogggeujeog.common.fixture;

import com.rollingpaper.ggeujeogggeujeog.user.domain.Role;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

public class UserTestFixture {

	public static class TestUser {
		public static User USER1 = User.builder()
			.id(1L)
			.email("tester1@tester.com")
			.password("1Q2w3e4r!@")
			.nickname("tester1")
			.role(Role.DEFAULT)
			.verified(true)
			.build();

		public static User USER2 = User.builder()
			.id(1L)
			.email("tester2@tester.org")
			.password("1Q2w3e4r!@#$")
			.nickname("tester2")
			.role(Role.DEFAULT)
			.verified(true)
			.build();
	}
}
