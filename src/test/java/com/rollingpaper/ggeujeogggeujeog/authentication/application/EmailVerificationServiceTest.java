package com.rollingpaper.ggeujeogggeujeog.authentication.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import javax.mail.MessagingException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rollingpaper.ggeujeogggeujeog.authentication.domain.EmailMessage;
import com.rollingpaper.ggeujeogggeujeog.authentication.infrastructure.EmailSender;
import com.rollingpaper.ggeujeogggeujeog.authentication.infrastructure.TokenRepository;

@ExtendWith(MockitoExtension.class)
class EmailVerificationServiceTest {

	@InjectMocks
	private EmailVerificationService emailVerificationService;

	@Mock
	private TokenRepository tokenRepository;

	@Mock
	private EmailSender emailSender;

	@Test
	@DisplayName("저장소에 토큰이 없다면 예외가 발생한다.")
	void throwExceptionIfTokenNotExist() {
		//given
		given(tokenRepository.getToken(any())).willThrow(IllegalArgumentException.class);

		//then
		assertThrows(
			IllegalArgumentException.class,
			() -> emailVerificationService.verify("test@email.com", "token")
		);
	}

	@Test
	@DisplayName("저장소에 토큰은 있지만, 토큰 값이 일치하지 않다면 예외가 발생한다.")
	void throwExceptionWhenTokenNotSame() {
		//given
		given(tokenRepository.getToken(anyString())).willReturn("token");

		//then
		assertThrows(
			IllegalArgumentException.class,
			() -> emailVerificationService.verify("test@email.com", "not-same")
		);
	}

	@Test
	@DisplayName("이메일 전송에 실패할 경우, 예외가 발생한다.")
	void throwExceptionWhenFailedToSend() throws MessagingException {
		//given
		willThrow(MessagingException.class).given(emailSender).sendEmail(isA(EmailMessage.class));

		//then
		then(tokenRepository).should(never()).storeToken(anyString(), anyString());
		assertThrows(
			IllegalArgumentException.class,
			() -> emailVerificationService.sendRegistrationMail("test@email.com", "token")
		);
	}
}