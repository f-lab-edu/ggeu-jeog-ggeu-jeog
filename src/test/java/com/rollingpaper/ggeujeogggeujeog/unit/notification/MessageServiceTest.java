package com.rollingpaper.ggeujeogggeujeog.unit.notification;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rollingpaper.ggeujeogggeujeog.common.fixture.PaperTestFixture;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture;
import com.rollingpaper.ggeujeogggeujeog.notification.application.MessageService;
import com.rollingpaper.ggeujeogggeujeog.notification.application.dto.MessageRequestDto;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationType;
import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.NotificationMapper;
import com.rollingpaper.ggeujeogggeujeog.outbox.domain.EventRepository;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

	@InjectMocks
	private MessageService messageService;

	@Mock
	private NotificationMapper notificationMapper;

	@Mock
	private EventRepository eventRepository;

	@Test
	@DisplayName("메시지 전송에 성공한다.")
	void successSendingCommentMessage() {
		//given
		MessageRequestDto dto = new MessageRequestDto(
			UserTestFixture.TestUser.USER1,
			UserTestFixture.TestUser.USER2,
			PaperTestFixture.TestPaper.PAPER1.getContent(),
			NotificationType.PAPER,
			PaperTestFixture.TestPaper.PAPER1.getId()
		);

		//when
		messageService.sendMessage(dto);

		//then
		then(notificationMapper).should(times(1)).saveEntity(any());
		then(eventRepository).should(times(1)).sendEvent(any());
	}
}
