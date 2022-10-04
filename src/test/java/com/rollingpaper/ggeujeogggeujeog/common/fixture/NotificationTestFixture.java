package com.rollingpaper.ggeujeogggeujeog.common.fixture;

import com.rollingpaper.ggeujeogggeujeog.notification.domain.Notification;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationMessage;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationStatus;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationType;

public class NotificationTestFixture {

	public static class TestNotification {
		public static Notification NOTIFICATION1 = Notification.builder()
			.id(1L)
			.message(
				NotificationMessage.builder()
					.title("TestUser1 - notification title - paper1")
					.content("TestUser1 - content")
					.typeId(PaperTestFixture.TestPaper.PAPER1.getBoardId())
					.type(NotificationType.PAPER)
					.build()
			)
			.status(NotificationStatus.ALIVE)
			.userId(UserTestFixture.TestUser.USER1.getId())
			.build();

		public static Notification NOTIFICATION2 = Notification.builder()
			.id(3L)
			.message(
				NotificationMessage.builder()
					.title("TestUser1 - notification title - comment1")
					.content("TestUser1 - content")
					.typeId(CommentTestFixture.TestComment.COMMENT1.getId())
					.type(NotificationType.COMMENT)
					.build()
			)
			.status(NotificationStatus.DELETED)
			.userId(UserTestFixture.TestUser.USER1.getId())
			.build();
	}
}
