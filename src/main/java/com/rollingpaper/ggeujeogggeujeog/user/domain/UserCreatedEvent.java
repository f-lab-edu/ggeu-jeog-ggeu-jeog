package com.rollingpaper.ggeujeogggeujeog.user.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rollingpaper.ggeujeogggeujeog.common.constant.MailSubjectConst;
import com.rollingpaper.ggeujeogggeujeog.event.domain.Outboxable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserCreatedEvent implements Outboxable {

	private static ObjectMapper MAPPER = new ObjectMapper();

	private final Long userId;
	private final JsonNode payload;

	public UserCreatedEvent(Long userId, JsonNode payload) {
		this.userId = userId;
		this.payload = payload;
	}

	public static UserCreatedEvent of(User user, String token) {

		JsonNode event = MAPPER.createObjectNode()
			.put("to", user.getEmail())
			.put("subject", MailSubjectConst.SUBJECT)
			.put("message", token);

		return new UserCreatedEvent(
			user.getId(),
			event
		);
	}

	@Override
	public String getAggregateId() {
		return String.valueOf(userId);
	}

	@Override
	public String getAggregateType() {
		return User.class.getName();
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
