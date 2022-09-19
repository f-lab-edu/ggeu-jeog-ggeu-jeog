package com.rollingpaper.ggeujeogggeujeog.authentication.application;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import com.rollingpaper.ggeujeogggeujeog.authentication.domain.EmailMessage;
import com.rollingpaper.ggeujeogggeujeog.authentication.infrastructure.EmailSender;
import com.rollingpaper.ggeujeogggeujeog.authentication.infrastructure.TokenRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class EmailVerificationService {

	private final TokenRepository tokenRepository;
	private final EmailSender emailSender;

	public void sendRegistrationMail(String email, String token) {
		EmailMessage message = new EmailMessage(
			email,
			"Verify Account",
			token
		);
		try {
			emailSender.sendEmail(message);
			tokenRepository.storeToken(email, token);
			log.info(email + " : " + token + " has been sent.");
		} catch (MessagingException e) {
			log.error("failed to send a email.");
			throw new IllegalArgumentException("이메일 전송에 실패했습니다.");
		}
	}

	public void verify(String email, String token) {
		String storedToken = tokenRepository.getToken(email);
		if (!storedToken.equals(token)) {
			throw new IllegalArgumentException("일치하지 않는 토큰입니다.");
		}
	}
}
