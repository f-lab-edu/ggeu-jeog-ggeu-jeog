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
			.id(2L)
			.email("helloworld@world.com")
			.password("Tester1234!")
			.nickname("tester2")
			.role(Role.DEFAULT)
			.verified(true)
			.build();

		public static User USER3 = User.builder()
			.id(3L)
			.email("tester3@tester.org")
			.password("Tester1234@")
			.nickname("tester3")
			.role(Role.DEFAULT)
			.verified(true)
			.build();
	}
}
