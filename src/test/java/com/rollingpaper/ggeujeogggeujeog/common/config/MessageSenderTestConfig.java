package com.rollingpaper.ggeujeogggeujeog.common.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.rollingpaper.ggeujeogggeujeog.notification.domain.MessageSender;
import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.dto.NotificationRequestDto;

@TestConfiguration
public class MessageSenderTestConfig {

	class DefaultMessageSender implements MessageSender {

		@Override
		public void sendNotification(NotificationRequestDto dto) {}
	}

	@Bean
	public MessageSender messageSender() {
		return new DefaultMessageSender();
	}
}
