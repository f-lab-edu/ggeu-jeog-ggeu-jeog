package com.rollingpaper.ggeujeogggeujeog.outbox.infrastructure;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.outbox.domain.Event;

@Repository
@Mapper
public interface OutBoxMapper {

	void saveEvent(Event event);

	List<Event> findAllByAggregateType(String type);

	void deleteEvent(Event event);

	Optional<Event> findEventById(Long id);

	List<Event> findAllNotifications(Long _skiprows, Long _pagesize);
}
