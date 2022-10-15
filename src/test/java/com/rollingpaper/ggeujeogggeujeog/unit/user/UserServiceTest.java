package com.rollingpaper.ggeujeogggeujeog.unit.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rollingpaper.ggeujeogggeujeog.user.application.UserService;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.UserUpdateRequestDto;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture;
import com.rollingpaper.ggeujeogggeujeog.common.util.PasswordEncoder;
import com.rollingpaper.ggeujeogggeujeog.user.exception.NoSuchUserException;
import com.rollingpaper.ggeujeogggeujeog.user.infrastructure.UserMapper;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private UserMapper userMapper;

	@Test
	@DisplayName("현재 회원의 정보를 수정한다")
	void update() {
		//given
		UserUpdateRequestDto dto = new UserUpdateRequestDto("tester1234", "1Q2w3e4r!@#$");
		given(userMapper.findById(any())).willReturn(Optional.ofNullable(UserTestFixture.TestUser.USER1));
		given(passwordEncoder.encode(any())).willReturn(dto.getPassword());

		//when
		userService.update(dto, UserTestFixture.TestUser.USER1.getId());

		//then
		then(userMapper).should(times(1)).update(any());
	}

	@Test
	@DisplayName("현재 회원이 없다면 수정에 실패한다")
	void updateWithUserNotFound() {
		//given
		UserUpdateRequestDto dto = new UserUpdateRequestDto("tester1234", "1Q2w3e4r!@#$");
		given(userMapper.findById(any())).willReturn(Optional.ofNullable(null));

		//then
		assertThrows(NoSuchUserException.class, () -> userService.update(dto, UserTestFixture.TestUser.USER1.getId()));
	}
}