package com.rollingpaper.ggeujeogggeujeog.integration.notification;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.common.config.AbstractContainerBaseTest;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.NotificationTestFixture;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture;
import com.rollingpaper.ggeujeogggeujeog.notification.application.NotificationFeedService;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.Notification;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationStatus;

@AutoConfigureMockMvc
public class NotificationIntegrationTest extends AbstractContainerBaseTest {

	@Autowired
	private NotificationFeedService notificationFeedService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("GET /api/v1/users/1/notifications - 사용자는 알림을 조회할 수 있다.")
	void getNotificationsByUser() throws Exception {
		//given
		Long userId = UserTestFixture.TestUser.USER1.getId();

		//when, then
		this.mockMvc.perform(get("/api/v1/users/{userId}/notifications", userId)
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();

	}

	@Test
	@Transactional
	@DisplayName("알림을 삭제 후 조회 시 알림 상태는 변경된다.")
	void deleteNotificationWithSuccess() {
		//given
		Long notificationId = NotificationTestFixture.TestNotification.NOTIFICATION1.getId();

		//when
		notificationFeedService.deleteNotification(notificationId);
		Notification notification = notificationFeedService.findById(notificationId);

		//then
		Assertions.assertEquals(NotificationStatus.DELETED, notification.getStatus());
	}
}
