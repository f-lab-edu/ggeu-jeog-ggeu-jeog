package com.rollingpaper.ggeujeogggeujeog.notification.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationMessage {

	private String title;
	private String content;
	private NotificationType type;
	private Long typeId;

	@Builder
	public NotificationMessage(String title, String content,
		NotificationType type, Long typeId) {
		this.title = title;
		this.content = content;
		this.type = type;
		this.typeId = typeId;
	}
}
