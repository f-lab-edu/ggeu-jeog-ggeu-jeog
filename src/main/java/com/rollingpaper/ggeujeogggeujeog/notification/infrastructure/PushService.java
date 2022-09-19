package com.rollingpaper.ggeujeogggeujeog.notification.infrastructure;

import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.dto.NotificationRequestDto;

public interface PushService {

	default void sendMessage(NotificationRequestDto dto) {
		throw new UnsupportedOperationException("지원하지 않는 메소드입니다.");
	}
}
