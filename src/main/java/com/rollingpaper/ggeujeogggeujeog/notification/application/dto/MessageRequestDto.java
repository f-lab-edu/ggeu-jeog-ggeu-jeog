package com.rollingpaper.ggeujeogggeujeog.notification.application.dto;

import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationType;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.Getter;

@Getter
public class MessageRequestDto {

	private User sender;
	private User receiver;
	private String content;
	private NotificationType type;
	private Long typeId;

	public MessageRequestDto(User sender, User receiver, String content,
		NotificationType type, Long typeId) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.type = type;
		this.typeId = typeId;
	}
}
