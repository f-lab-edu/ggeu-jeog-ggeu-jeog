package com.rollingpaper.ggeujeogggeujeog.notification.domain;

import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.dto.NotificationRequestDto;

public interface MessageSender {

	default void sendNotification(NotificationRequestDto dto) {
		throw new UnsupportedOperationException("지원하지 않는 메소드입니다.");
	}
}
