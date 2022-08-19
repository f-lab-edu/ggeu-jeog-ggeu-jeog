package com.rollingpaper.ggeujeogggeujeog.user.application;

import static com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rollingpaper.ggeujeogggeujeog.common.util.PasswordEncoder;
import com.rollingpaper.ggeujeogggeujeog.user.exception.DuplicatedEmailException;
import com.rollingpaper.ggeujeogggeujeog.user.exception.NoSuchUserException;
import com.rollingpaper.ggeujeogggeujeog.user.infrastructure.UserMapper;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.SignInRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.SignUpRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.UserUpdateRequestDto;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserMapper userMapper;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private HttpSession httpSession;

	@Test
	@DisplayName("중복된 이메일이 없다면 회원가입에 성공한다")
	void register() {
		//given
		SignUpRequestDto dto = SignUpRequestDto.builder()
			.email(TestUser.USER1.getEmail())
			.password(TestUser.USER1.getPassword())
			.nickname(TestUser.USER1.getNickname())
			.build();
		given(userMapper.findByEmail(any())).willReturn(Optional.ofNullable(null));

		//when
		userService.register(dto);

		//then
		then(userMapper).should(times(1)).save(any());
	}

	@Test
	@DisplayName("중복된 이메일이 있다면 회원가입에 실패한다")
	void registerWithDuplicatedEmail() {
		//given
		SignUpRequestDto dto = SignUpRequestDto.builder()
			.email(TestUser.USER1.getEmail())
			.password(TestUser.USER1.getPassword())
			.nickname(TestUser.USER1.getNickname())
			.build();
		given(userMapper.findByEmail(any())).willReturn(Optional.ofNullable(SignUpRequestDto.toEntity(dto)));

		//then
		assertThrows(DuplicatedEmailException.class, () -> userService.register(dto));
	}

	@Test
	@DisplayName("회원이 존재하고 패스워드가 일치하면 로그인에 성공한다")
	void signIn() {
		//given
		SignInRequestDto dto = new SignInRequestDto(TestUser.USER1.getEmail(), TestUser.USER1.getPassword());
		given(userMapper.findByEmail(any())).willReturn(Optional.ofNullable(TestUser.USER1));
		given(passwordEncoder.matches(TestUser.USER1.getPassword(), TestUser.USER1.getPassword())).willReturn(true);

		//when
		userService.signIn(dto);

		//then
		then(httpSession).should(times(1)).setAttribute(any(), any());
	}

	@Test
	@DisplayName("이메일이 일치하지 않으면 로그인에 실패한다")
	void signInWithEmailNotFound() {
		//given
		SignInRequestDto dto = new SignInRequestDto(TestUser.USER1.getEmail(), TestUser.USER1.getPassword());
		given(userMapper.findByEmail(any())).willReturn(Optional.ofNullable(null));

		//then
		assertThrows(NoSuchUserException.class, () -> userService.signIn(dto));
	}

	@Test
	@DisplayName("회원은 존재하고 패스워드가 틀릴 경우 로그인에 실패한다")
	void signInWithPasswordNotFound() {
		//given
		SignInRequestDto dto = new SignInRequestDto(TestUser.USER1.getEmail(), TestUser.USER1.getPassword());
		given(userMapper.findByEmail(any())).willReturn(Optional.ofNullable(TestUser.USER1));
		given(passwordEncoder.matches(any(), any())).willReturn(false);

		//then
		assertThrows(IllegalArgumentException.class, () -> userService.signIn(dto));
	}

	@Test
	@DisplayName("현재 회원의 정보를 수정한다")
	void update() {
		//given
		UserUpdateRequestDto dto = new UserUpdateRequestDto("tester1234", "1Q2w3e4r!@#$");
		given(userMapper.findById(any())).willReturn(Optional.ofNullable(TestUser.USER1));
		given(passwordEncoder.encode(any())).willReturn(dto.getPassword());

		//when
		userService.update(dto, TestUser.USER1.getId());

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
		assertThrows(NoSuchUserException.class, () -> userService.update(dto, TestUser.USER1.getId()));
	}
}