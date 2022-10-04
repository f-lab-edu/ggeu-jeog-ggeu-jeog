package com.rollingpaper.ggeujeogggeujeog.outbox.domain;

import lombok.Getter;

@Getter
public class Event {

	private Long id;
	private String aggregateType;
	private Long aggregateId;
	private EventType type;
	private String payload;

	public Event() {}

	public Event(String aggregateType, Long aggregateId,
		EventType type, String payload) {
		this.aggregateType = aggregateType;
		this.aggregateId = aggregateId;
		this.type = type;
		this.payload = payload;
	}
}
