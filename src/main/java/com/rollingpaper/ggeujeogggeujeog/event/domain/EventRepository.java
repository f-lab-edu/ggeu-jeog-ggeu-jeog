package com.rollingpaper.ggeujeogggeujeog.event.domain;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

	void sendEvent(Event event);

	List<Event> findAllByAggregateType(String type);

	void deleteEvent(Event event);

	Optional<Event> findEventById(Long id);

	List<Event> findAllNotifications(Long _skiprows, Long _pagesize);
}
