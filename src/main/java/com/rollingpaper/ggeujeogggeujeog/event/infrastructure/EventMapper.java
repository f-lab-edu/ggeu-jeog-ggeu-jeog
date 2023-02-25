package com.rollingpaper.ggeujeogggeujeog.event.infrastructure;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.rollingpaper.ggeujeogggeujeog.event.domain.Event;

@Mapper
public interface EventMapper {

	void saveEvent(Event event);

	List<Event> findAllByAggregateType(String type);

	void deleteEvent(Event event);

	Optional<Event> findEventById(Long id);

	List<Event> findAllNotifications(Long _skiprows, Long _pagesize);
}
