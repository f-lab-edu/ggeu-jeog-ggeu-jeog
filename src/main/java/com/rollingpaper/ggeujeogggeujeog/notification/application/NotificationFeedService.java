package com.rollingpaper.ggeujeogggeujeog.notification.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.notification.domain.Notification;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationRepository;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationStatus;
import com.rollingpaper.ggeujeogggeujeog.notification.exception.NoSuchNotification;
import com.rollingpaper.ggeujeogggeujeog.notification.presentation.dto.NotificationsDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NotificationFeedService {

	private final NotificationRepository notificationRepository;

	@Transactional(readOnly = true)
	public NotificationsDto getUserNotifications(Long userId) {
		List<Notification> notificationList = notificationRepository.findAllByUserId(userId);
		return NotificationsDto.from(notificationList);
	}

	@Transactional
	public void deleteNotification(Long notificationId) {
		Notification notification = findById(notificationId);
		notification.updateStatus(NotificationStatus.DELETED);
		notificationRepository.update(notification);
	}

	@Transactional
	public Notification findById(Long notificationId) {
		return notificationRepository.findNotificationById(notificationId)
			.orElseThrow(NoSuchNotification::new);
	}
}
