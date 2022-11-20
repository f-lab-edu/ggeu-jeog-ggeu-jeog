package com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rollingpaper.ggeujeogggeujeog.event.domain.Event;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.Notification;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationMessage;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationStatus;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@Slf4j
public class NotificationRequestDto {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private NotificationMessage message;
	private User user;

	@Builder
	public NotificationRequestDto(NotificationMessage message, User user) {
		this.message = message;
		this.user = user;
	}

	public static Notification createNotificationEntity(NotificationRequestDto dto) {
		return Notification.builder()
			.message(dto.getMessage())
			.status(NotificationStatus.ALIVE)
			.userId(dto.getUser().getId())
			.build();
	}

	public static NotificationRequestDto from(Event event) {
		try {
			return MAPPER.readValue(event.getPayload(), NotificationRequestDto.class);
		} catch (JsonProcessingException e) {
			log.error("failed to convert json to object");
			throw new RuntimeException("객체 변환에 실패했습니다.");
		}
	}
}
