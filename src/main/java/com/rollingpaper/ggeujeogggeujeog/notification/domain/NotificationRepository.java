package com.rollingpaper.ggeujeogggeujeog.notification.domain;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository {

	List<Notification> findAllByUserId(Long userId);

	Optional<Notification> findNotificationById(Long notificationId);

	void update(Notification notification);

	void saveEntity(Notification notification);
}
