package com.rollingpaper.ggeujeogggeujeog.event.domain;

public interface Outboxable {

	String getAggregateId();

	String getAggregateType();

	String getPayload();

	String getType();
}
