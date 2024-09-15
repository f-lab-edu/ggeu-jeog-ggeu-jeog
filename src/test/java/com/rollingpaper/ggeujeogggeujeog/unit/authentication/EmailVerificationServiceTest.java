package com.rollingpaper.ggeujeogggeujeog.unit.authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rollingpaper.ggeujeogggeujeog.authentication.application.EmailVerificationService;
import com.rollingpaper.ggeujeogggeujeog.authentication.domain.TokenRepository;
import com.rollingpaper.ggeujeogggeujeog.authentication.exception.NotSameTokenException;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture;
import com.rollingpaper.ggeujeogggeujeog.event.application.EventService;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;
import com.rollingpaper.ggeujeogggeujeog.user.domain.UserRepository;

@ExtendWith(MockitoExtension.class)
class EmailVerificationServiceTest {

	@InjectMocks
	private EmailVerificationService emailVerificationService;

	@Mock
	private TokenRepository tokenRepository;

	@Mock
	private EventService eventService;

	@Mock
	private UserRepository userRepository;

	@Test
	@DisplayName("저장소에 토큰이 없다면 예외가 발생한다.")
	void throwExceptionIfTokenNotExist() {
		//given
		given(tokenRepository.getToken(any())).willThrow(IllegalArgumentException.class);

		//then
		assertThrows(
			IllegalArgumentException.class,
			() -> emailVerificationService.verify(UserTestFixture.TestUser.USER1, "token")
		);
	}

	@Test
	@DisplayName("저장소에 토큰은 있지만, 토큰 값이 일치하지 않다면 예외가 발생한다.")
	void throwExceptionWhenTokenNotSame() {
		//given
		given(tokenRepository.getToken(anyString())).willReturn("token");

		//then
		assertThrows(
			NotSameTokenException.class,
			() -> emailVerificationService.verify(UserTestFixture.TestUser.USER1, "not-same")
		);
	}

	@Test
	@DisplayName("토큰 값을 검증한 뒤, 회원 권한을 업데이트한다.")
	void updateUserAfterEmailVerification() {
		//given
		User testUser = UserTestFixture.TestUser.USER1;
		String testToken = "token";
		given(tokenRepository.getToken(anyString())).willReturn("token");

		//when
		emailVerificationService.verify(testUser, testToken);

		//then
		then(userRepository).should(times(1)).update(any());
	}

	@Test
	@DisplayName("회원 가입 후 토큰을 저장하고 이메일 이벤트를 큐에 넣는다.")
	void storeTokenAndSendEmailAfterRegistration() {
		//given
		User testUser = UserTestFixture.TestUser.USER1;
		String testToken = "token";

		//when
		emailVerificationService.sendRegistrationMail(testUser, testToken);

		//then
		then(tokenRepository).should(times(1)).storeToken(anyString(), anyString());
		then(eventService).should(times(1)).publish(any());
	}
}