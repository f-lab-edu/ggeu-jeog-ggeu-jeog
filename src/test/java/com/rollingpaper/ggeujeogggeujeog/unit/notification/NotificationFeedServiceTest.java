package com.rollingpaper.ggeujeogggeujeog.unit.notification;

import static com.rollingpaper.ggeujeogggeujeog.common.fixture.NotificationTestFixture.*;
import static com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rollingpaper.ggeujeogggeujeog.notification.application.NotificationFeedService;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.Notification;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationRepository;
import com.rollingpaper.ggeujeogggeujeog.notification.exception.NoSuchNotification;

@ExtendWith(MockitoExtension.class)
class NotificationFeedServiceTest {

	@InjectMocks
	private NotificationFeedService notificationFeedService;

	@Mock
	private NotificationRepository notificationRepository;

	@Test
	@DisplayName("알림 리스트를 사용자 id를 통해 조회한다.")
	void findAllNotifications() {
		//when
		notificationFeedService.getUserNotifications(TestUser.USER1.getId());

		//then
		then(notificationRepository).should(times(1)).findAllByUserId(anyLong());
	}

	@Test
	@DisplayName("알림을 id를 통해 조회한다.")
	void findNotification() {
		//given
		given(notificationRepository.findNotificationById(anyLong())).willReturn(
			Optional.of(TestNotification.NOTIFICATION1)
		);

		//when
		Notification notification = notificationFeedService.findById(
			TestNotification.NOTIFICATION1.getId()
		);

		//then
		Assertions.assertEquals(notification, TestNotification.NOTIFICATION1);
	}

	@Test
	@DisplayName("알림이 삭제할 때, 알림이 없다면 예외가 발생한다.")
	void deleteNotificationWithException() {
		//given
		given(notificationRepository.findNotificationById(anyLong())).willThrow(NoSuchNotification.class);

		//when, then
		Assertions.assertThrows(NoSuchNotification.class,
			() -> notificationFeedService.findById(
				TestNotification.NOTIFICATION1.getId()
			)
		);
	}

	@Test
	@DisplayName("현재 알림을 조회한 뒤, 삭제한다.")
	void deleteNotification() {
		//given
		given(notificationRepository.findNotificationById(anyLong())).willReturn(
			Optional.of(TestNotification.NOTIFICATION1)
		);

		//when
		notificationFeedService.deleteNotification(TestNotification.NOTIFICATION1.getId());

		//then
		then(notificationRepository).should(times(1)).update(any());
	}
}