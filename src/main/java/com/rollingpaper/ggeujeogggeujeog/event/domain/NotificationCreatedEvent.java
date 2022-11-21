package com.rollingpaper.ggeujeogggeujeog.event.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.dto.NotificationRequestDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotificationCreatedEvent extends Event {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private NotificationCreatedEvent(String aggregateType, Long aggregateId,
		EventType type, String payload) {
		// super(aggregateType, aggregateId, type, payload);
	}

	public static NotificationCreatedEvent of(NotificationRequestDto dto, Long notificationId) {

		String payload;

		try {
			payload = MAPPER.writeValueAsString(dto);
		} catch (JsonProcessingException e) {
			log.error("failed to process json", e);
			payload = "";
		}

		return new NotificationCreatedEvent(
				"notification",
				notificationId,
				EventType.INSERT,
				payload
			);
	}

	public static NotificationRequestDto convertEventToDto(Event event) {
		try {
			return MAPPER.readValue(event.getPayload(), NotificationRequestDto.class);
		} catch (JsonProcessingException e) {
			log.error("failed to convert json to object");
			throw new RuntimeException("객체 변환에 실패했습니다.");
		}
	}
}
