package com.rollingpaper.ggeujeogggeujeog.event.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.event.domain.Event;
import com.rollingpaper.ggeujeogggeujeog.event.domain.EventRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class EventRepositoryAdaptor implements EventRepository {

	private final EventMapper eventMapper;

	@Override
	public void sendEvent(Event event) {
		eventMapper.saveEvent(event);
	}

	@Override
	public List<Event> findAllByAggregateType(String type) {
		return eventMapper.findAllByAggregateType(type);
	}

	@Override
	public void deleteEvent(Event event) {
		eventMapper.deleteEvent(event);
	}

	@Override
	public Optional<Event> findEventById(Long id) {
		return eventMapper.findEventById(id);
	}

	@Override
	public List<Event> findAllNotifications(Long _skiprows, Long _pagesize) {
		return eventMapper.findAllNotifications(_skiprows, _pagesize);
	}
}
