package com.rollingpaper.ggeujeogggeujeog.event.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.event.domain.Event;
import com.rollingpaper.ggeujeogggeujeog.event.domain.EventRepository;
import com.rollingpaper.ggeujeogggeujeog.event.domain.Outboxable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EventService {

	private final EventRepository eventRepository;

	@Transactional
	public void sendEvent(Outboxable outboxable) {
		eventRepository.sendEvent(Event.from(outboxable));
	}
}
