package com.rollingpaper.ggeujeogggeujeog.authentication.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.authentication.domain.TokenRepository;
import com.rollingpaper.ggeujeogggeujeog.authentication.exception.NotSameTokenException;
import com.rollingpaper.ggeujeogggeujeog.event.application.EventService;
import com.rollingpaper.ggeujeogggeujeog.user.domain.UserCreatedEvent;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;
import com.rollingpaper.ggeujeogggeujeog.user.domain.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class EmailVerificationService {

	private final TokenRepository tokenRepository;
	private final EventService eventService;
	private final UserRepository userRepository;

	@Transactional
	public void sendRegistrationMail(User user, String token) {
		tokenRepository.storeToken(user.getEmail(), token);
		eventService.publish(UserCreatedEvent.of(user, token));
	}

	@Transactional
	public void verify(User user, String token) {
		String storedToken = tokenRepository.getToken(user.getEmail());
		if (!storedToken.equals(token)) {
			throw new NotSameTokenException();
		}
		user.verifiedUser();
		userRepository.update(user);
	}
}
