package com.rollingpaper.ggeujeogggeujeog.outbox.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {

	private Long id;
	private String aggregateType;
	private Long aggregateId;
	private EventType type;
	private String payload;
	private boolean deleted;

	public Event() {}

	public Event(String aggregateType, Long aggregateId,
		EventType type, String payload) {
		this.aggregateType = aggregateType;
		this.aggregateId = aggregateId;
		this.type = type;
		this.payload = payload;
	}

	public void deleteEvent() {
		this.deleted = true;
	}
}
