package com.rollingpaper.ggeujeogggeujeog.notification.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.notification.domain.Notification;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationStatus;
import com.rollingpaper.ggeujeogggeujeog.notification.exception.NoSuchNotification;
import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.NotificationMapper;
import com.rollingpaper.ggeujeogggeujeog.notification.presentation.dto.NotificationsDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NotificationFeedService {

	private final NotificationMapper notificationMapper;

	@Transactional(readOnly = true)
	public NotificationsDto getUserNotifications(Long userId) {
		List<Notification> notificationList = notificationMapper.findAllByUserId(userId);
		return NotificationsDto.from(notificationList);
	}

	@Transactional
	public void deleteNotification(Long notificationId) {
		Notification notification = findById(notificationId);
		notification.updateStatus(NotificationStatus.DELETED);
		notificationMapper.update(notification);
	}

	@Transactional
	public Notification findById(Long notificationId) {
		return notificationMapper.findNotificationById(notificationId)
			.orElseThrow(NoSuchNotification::new);
	}
}
