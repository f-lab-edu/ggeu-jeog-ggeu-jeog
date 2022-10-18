package com.rollingpaper.ggeujeogggeujeog.notification.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.notification.application.dto.MessageRequestDto;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.Notification;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationMessage;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationType;
import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.NotificationMapper;
import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.dto.NotificationRequestDto;
import com.rollingpaper.ggeujeogggeujeog.outbox.domain.EventRepository;
import com.rollingpaper.ggeujeogggeujeog.outbox.domain.NotificationCreatedEvent;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MessageService {

	private final NotificationMapper notificationMapper;
	private final EventRepository eventRepository;

	@Transactional
	public void sendMessage(MessageRequestDto dto) {
		NotificationRequestDto notificationRequestDto = createNotification(dto);
		Notification entity = NotificationRequestDto.createNotificationEntity(notificationRequestDto);

		notificationMapper.saveEntity(entity);
		eventRepository.sendEvent(NotificationCreatedEvent.of(notificationRequestDto, entity.getId()));
	}

	private NotificationRequestDto createNotification(MessageRequestDto dto) {

		NotificationMessage message = NotificationMessage.builder()
			.title(createNotificationTitle(dto.getSender(), dto.getReceiver(), dto.getType()))
			.content(dto.getContent())
			.type(dto.getType())
			.typeId(dto.getTypeId())
			.build();

		return NotificationRequestDto.builder()
			.message(message)
			.user(dto.getReceiver())
			.build();
	}

	private String createNotificationTitle(User sender, User receiver, NotificationType type) {
		switch (type) {
			case COMMENT:
				return String.format(
					"%s 님, %s께서 댓글을 남겼습니다.",
					receiver.getNickname(),
					sender.getNickname()
				);
			case PAPER:
				return String.format(
					"%s 님, %s께서 페이퍼를 남겼습니다.",
					receiver.getNickname(),
					sender.getNickname()
				);
			default:
				return String.format(
					"%s 님, %s님이 남긴 알림이 있습니다.",
					receiver.getNickname(),
					sender.getNickname()
				);
		}
	}
}
