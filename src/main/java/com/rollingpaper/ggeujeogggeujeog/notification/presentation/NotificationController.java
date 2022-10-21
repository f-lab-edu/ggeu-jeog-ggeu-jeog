package com.rollingpaper.ggeujeogggeujeog.notification.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rollingpaper.ggeujeogggeujeog.notification.application.NotificationFeedService;
import com.rollingpaper.ggeujeogggeujeog.notification.presentation.dto.NotificationsDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class NotificationController {

	private final NotificationFeedService notificationFeedService;

	@GetMapping("/{userId}/notifications")
	public ResponseEntity<NotificationsDto> getNotifications(
		@PathVariable Long userId
	) {
		NotificationsDto userNotifications =
			notificationFeedService.getUserNotifications(userId);
		return ResponseEntity.ok(userNotifications);
	}
}
