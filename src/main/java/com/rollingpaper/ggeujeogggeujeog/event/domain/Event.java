package com.rollingpaper.ggeujeogggeujeog.event.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {

	private Long id;
	private String aggregateType;
	private String aggregateId;
	private String type;
	private String payload;
	private boolean deleted;

	public Event() {}

	@Builder
	public Event(Long id, String aggregateType, String aggregateId, String type, String payload) {
		this.id = id;
		this.aggregateType = aggregateType;
		this.aggregateId = aggregateId;
		this.type = type;
		this.payload = payload;
	}

	public void deleteEvent() {
		this.deleted = true;
	}

	public static Event from(Outboxable outboxable) {
		return Event.builder()
			.aggregateType(outboxable.getAggregateType())
			.aggregateId(outboxable.getAggregateId())
			.type(outboxable.getType())
			.payload(outboxable.getPayload())
			.build();
	}
}
