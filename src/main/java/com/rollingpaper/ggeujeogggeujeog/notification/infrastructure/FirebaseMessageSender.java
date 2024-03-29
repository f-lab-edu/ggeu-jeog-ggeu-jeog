package com.rollingpaper.ggeujeogggeujeog.notification.infrastructure;

import java.io.IOException;

import javax.annotation.PostConstruct;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.MessageSender;
import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.dto.NotificationRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class FirebaseMessageSender implements MessageSender {

	@PostConstruct
	private void init() {
		FirebaseOptions options = null;
		try {
			options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.getApplicationDefault())
				.build();
		} catch (IOException e) {
			log.error("fail to connect firebase", e);
			throw new RuntimeException("firebase 연결 실패");
		}

		FirebaseApp.initializeApp(options);
	}

	@Override
	public void sendNotification(NotificationRequestDto dto) {
		Message firebaseMessage = MessageConverter.convertToFirebaseMessage(dto);
		try {
			FirebaseMessaging.getInstance().send(firebaseMessage);
		} catch (FirebaseMessagingException e) {
			log.error("fail to send message", e);
			throw new RuntimeException("메세지 전송에 실패했습니다.");
		}
	}
}
