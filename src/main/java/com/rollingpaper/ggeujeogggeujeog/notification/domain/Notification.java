package com.rollingpaper.ggeujeogggeujeog.notification.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Notification {

	private Long id;
	private NotificationStatus status;
	private Long userId;
	private NotificationMessage message;

	@Builder
	public Notification(Long id, NotificationStatus status, Long userId, NotificationMessage message) {
		this.id = id;
		this.status = status;
		this.userId = userId;
		this.message = message;
	}

	public void updateStatus(NotificationStatus status) {
		this.status = status;
	}
}
