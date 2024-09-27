package com.rollingpaper.ggeujeogggeujeog.integration.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.rollingpaper.ggeujeogggeujeog.common.config.AbstractContainerBaseTest;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture;
import com.rollingpaper.ggeujeogggeujeog.user.application.UserService;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;
import com.rollingpaper.ggeujeogggeujeog.user.exception.NoSuchUserException;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.UserUpdateRequestDto;

class UserIntegrationTest extends AbstractContainerBaseTest {

	@Autowired
	private UserService userService;

	@Test
	@DisplayName("존재하지 않는 유저는 프로필을 조회할 수 없다.")
	void getUserProfileWithNoSuchUser() {
		//given
		User user = User.builder()
			.id(10L)
			.nickname("Tester")
			.build();

		//when, then
		Assertions.assertThatThrownBy(() ->
			userService.getUserProfile(user.getId()))
			.isInstanceOf(NoSuchUserException.class)
			.hasFieldOrPropertyWithValue("errorCode", "002")
			.hasFieldOrPropertyWithValue("httpStatus", HttpStatus.BAD_REQUEST)
			.hasMessage("존재하지 않는 계정입니다.");
	}

	@Test
	@DisplayName("자신의 소유하는 프로필만 수정할 수 있다.")
	void updateProfileWithNotOwner() {
		//given
		User user = User.builder()
			.id(10L)
			.nickname("Tester")
			.build();
		UserUpdateRequestDto dto = new UserUpdateRequestDto(
			"tester",
			"Testing1234!"
		);

		Assertions.assertThatThrownBy(() ->
			userService.update(dto, user.getId()))
			.isInstanceOf(NoSuchUserException.class)
			.hasFieldOrPropertyWithValue("errorCode", "002")
			.hasFieldOrPropertyWithValue("httpStatus", HttpStatus.BAD_REQUEST)
			.hasMessage("존재하지 않는 계정입니다.");
	}

	@Test
	@DisplayName("사용자 본인만 계정을 삭제할 수 있다.")
	void deleteUserByOwner() {
		//when
		userService.delete(UserTestFixture.TestUser.USER1.getId());

		//then
		Assertions.assertThat(
			userService.getUserById(UserTestFixture.TestUser.USER1.getId()))
			.isEmpty();
	}
}
