package com.rollingpaper.ggeujeogggeujeog.notification.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Notification {

	private Long id;
	private NotificationMessage message;
	private NotificationStatus status;
	private Long userId;

	@Builder
	public Notification(Long id, NotificationMessage message,
		NotificationStatus status, Long userId) {
		this.id = id;
		this.message = message;
		this.status = status;
		this.userId = userId;
	}

	public void updateStatus(NotificationStatus status) {
		this.status = status;
	}
}
