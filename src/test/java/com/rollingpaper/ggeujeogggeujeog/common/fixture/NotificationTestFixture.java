package com.rollingpaper.ggeujeogggeujeog.common.fixture;

import com.rollingpaper.ggeujeogggeujeog.notification.domain.Notification;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationMessage;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationStatus;

public class NotificationTestFixture {

	public static class TestNotification {
		public static Notification NOTIFICATION1 = Notification.builder()
			.id(1L)
			.message(
				NotificationMessage.createCommentMessage(
						"test-noti-content-1",
						CommentTestFixture.TestComment.COMMENT1.getId())
			)
			.status(NotificationStatus.ALIVE)
			.userId(UserTestFixture.TestUser.USER1.getId())
			.build();

		public static Notification NOTIFICATION2 = Notification.builder()
			.id(3L)
			.message(
				NotificationMessage.createPaperMessage(
						"test-noti-paper-1",
						PaperTestFixture.TestPaper.PAPER1.getId())
			)
			.status(NotificationStatus.DELETED)
			.userId(UserTestFixture.TestUser.USER1.getId())
			.build();
	}
}
