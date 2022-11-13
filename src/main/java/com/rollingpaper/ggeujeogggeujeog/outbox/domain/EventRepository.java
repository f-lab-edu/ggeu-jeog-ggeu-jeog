package com.rollingpaper.ggeujeogggeujeog.outbox.domain;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.outbox.infrastructure.OutBoxMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EventRepository {

	private final OutBoxMapper outBoxMapper;

	@Transactional
	public void sendEvent(Event event) {
		outBoxMapper.saveEvent(event);
	}
}
