package com.rollingpaper.ggeujeogggeujeog.notification.presentation.dto;

import java.util.List;

import com.rollingpaper.ggeujeogggeujeog.notification.domain.Notification;

import lombok.Getter;

@Getter
public class NotificationsDto {

	private List<Notification> notifications;

	public NotificationsDto(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public static NotificationsDto from(List<Notification> notificationList) {
		return new NotificationsDto(notificationList);
	}
}
