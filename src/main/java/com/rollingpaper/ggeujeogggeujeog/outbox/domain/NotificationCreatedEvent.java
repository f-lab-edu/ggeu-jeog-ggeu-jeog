package com.rollingpaper.ggeujeogggeujeog.outbox.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotificationCreatedEvent extends Event {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	private NotificationCreatedEvent(String aggregateType, Long aggregateId,
		EventType type, String payload) {
		super(aggregateType, aggregateId, type, payload);
	}

	public static NotificationCreatedEvent of(Notification notification) {

		String payload;

		try {
			payload = objectMapper.writeValueAsString(notification);
		} catch (JsonProcessingException e) {
			log.error("failed to process json", e);
			payload = "";
		}

		return new NotificationCreatedEvent(
				"notification",
				notification.getId(),
				EventType.INSERT,
				payload
			);
	}
}
