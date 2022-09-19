package com.rollingpaper.ggeujeogggeujeog.notification.infrastructure;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.dto.NotificationRequestDto;

public class MessageConverter {

	public static Message convertToFirebaseMessage(NotificationRequestDto dto) {
		return Message.builder()
			.setNotification(
				Notification.builder()
					.setTitle(dto.getMessage().getTitle())
					.setBody(dto.getMessage().getContent())
					.build()
			)
			.setToken(dto.getUser().getAccessToken())
			.build();
	}
}
