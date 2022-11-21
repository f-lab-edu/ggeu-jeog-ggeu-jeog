package com.rollingpaper.ggeujeogggeujeog.event.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.Notification;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.NotificationMessage;
import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.dto.NotificationRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotificationInsertedEvent implements Outboxable {

	private static ObjectMapper MAPPER = new ObjectMapper();

	private final Long notificationId;
	private final JsonNode payload;

	public NotificationInsertedEvent(Long notificationId, JsonNode payload) {
		this.notificationId = notificationId;
		this.payload = payload;
	}

	public static NotificationInsertedEvent of(Notification notification, User user) {

		NotificationMessage message = notification.getMessage();

		JsonNode event = MAPPER.createObjectNode()
			.putPOJO("message", message)
			.putPOJO("user", user);


		return new NotificationInsertedEvent(
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
