package com.rollingpaper.ggeujeogggeujeog.notification.infrastructure;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.notification.domain.Notification;

@Repository
@Mapper
public interface NotificationMapper {

	List<Notification> findAllByUserId(Long userId);

	Optional<Notification> findById(Long notificationId);

	void update(Notification notification);

	void save(Notification notification);
}
