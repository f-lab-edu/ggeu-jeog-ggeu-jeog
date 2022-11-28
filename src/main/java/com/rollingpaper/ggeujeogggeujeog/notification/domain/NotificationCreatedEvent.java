package com.rollingpaper.ggeujeogggeujeog.notification.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rollingpaper.ggeujeogggeujeog.event.domain.Outboxable;
import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.dto.NotificationRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotificationCreatedEvent implements Outboxable {

	private static ObjectMapper MAPPER = new ObjectMapper();

	private final Long notificationId;
	private final JsonNode payload;

	public NotificationCreatedEvent(Long notificationId, JsonNode payload) {
		this.notificationId = notificationId;
		this.payload = payload;
	}

	public static NotificationCreatedEvent of(Notification notification, User user) {

		NotificationMessage message = notification.getMessage();

		JsonNode event = MAPPER.createObjectNode()
			.putPOJO("message", message)
			.putPOJO("user", user);


		return new NotificationCreatedEvent(
			notification.getId(),
			event
		);
	}

	@Override
	public String getAggregateId() {
		return String.valueOf(notificationId);
	}

	@Override
	public String getAggregateType() {
		return NotificationRequestDto.class.getName();
	}

	@Override
	public String getPayload() {
		try {
			return MAPPER.writeValueAsString(payload);
		} catch (JsonProcessingException e) {
			log.error("failed to convert json to object");
			throw new RuntimeException("객체 변환에 실패했습니다.");
		}
	}

	@Override
	public String getType() {
		return this.getClass().getName();
	}
}
