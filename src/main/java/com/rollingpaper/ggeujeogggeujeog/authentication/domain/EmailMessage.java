package com.rollingpaper.ggeujeogggeujeog.authentication.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rollingpaper.ggeujeogggeujeog.event.domain.Event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Getter
@Slf4j
public class EmailMessage {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private String to;
	private String subject;
	private String message;

	@Builder
	public EmailMessage(String to, String subject, String message) {
		this.to = to;
		this.subject = subject;
		this.message = message;
	}

	public static EmailMessage convertEventToMessage(Event event) {
		try {
			return MAPPER.readValue(event.getPayload(), EmailMessage.class);
		} catch (JsonProcessingException e) {
			log.error("failed to convert json to object");
			throw new RuntimeException("객체 변환에 실패했습니다.");
		}
	}
}
