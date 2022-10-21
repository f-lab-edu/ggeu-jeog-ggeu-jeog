package com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.dto;

import com.rollingpaper.ggeujeogggeujeog.notification.domain.Notification;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationMessage;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationStatus;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationRequestDto {

	private NotificationMessage message;
	private User user;

	@Builder
	public NotificationRequestDto(NotificationMessage message, User user) {
		this.message = message;
		this.user = user;
	}

	public static Notification createNotificationEntity(NotificationRequestDto dto) {
		return Notification.builder()
			.message(dto.getMessage())
			.status(NotificationStatus.ALIVE)
			.userId(dto.getUser().getId())
			.build();
	}

	@Override
	public String toString() {
		return "NotificationRequestDto{" +
			"message=" + message +
			", user=" + user +
			'}';
	}
}
