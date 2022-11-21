package com.rollingpaper.ggeujeogggeujeog.notification.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.notification.domain.Notification;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class NotificationRepositoryAdaptor implements NotificationRepository {

	private final NotificationMapper notificationMapper;

	@Override
	public List<Notification> findAllByUserId(Long userId) {
		return notificationMapper.findAllByUserId(userId);
	}

	@Override
	public Optional<Notification> findNotificationById(Long notificationId) {
		return notificationMapper.findNotificationById(notificationId);
	}

	@Override
	public void update(Notification notification) {
		notificationMapper.update(notification);
	}

	@Override
	public void saveEntity(Notification notification) {
		notificationMapper.saveEntity(notification);
	}
}
