package com.rollingpaper.ggeujeogggeujeog.outbox.infrastructure;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rollingpaper.ggeujeogggeujeog.notification.domain.MessageSender;
import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.dto.NotificationRequestDto;
import com.rollingpaper.ggeujeogggeujeog.outbox.domain.Event;
import com.rollingpaper.ggeujeogggeujeog.outbox.domain.NotificationCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class NotificationBatchTask {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final SqlSessionFactory sqlSessionFactory;
	private final MessageSender firebaseMessageSender;

	@Bean
	public Job notificationSendingJob() {
		log.debug("-------------------------------- create job --------------------------------");
		return jobBuilderFactory.get("notificationSendingJob")
			.start(startNotificationStep())
			.build();
	}

	@Bean
	@JobScope
	public Step startNotificationStep() {
		log.debug("-------------------------------- create step --------------------------------");
		return stepBuilderFactory.get("startNotificationStep")
			.<Event, Event>chunk(100)
			.reader(EventReader())
			.processor(EventProcessor())
			.writer(EventWriter())
			.build();
	}

	@Bean
	@StepScope
	public MyBatisPagingItemReader<Event> EventReader() {
		log.debug("-------------------------------- ItemReader started --------------------------------");
		return new MyBatisPagingItemReaderBuilder<Event>()
			.pageSize(100)
			.sqlSessionFactory(sqlSessionFactory)
			.queryId("com.rollingpaper.ggeujeogggeujeog.outbox.infrastructure.OutBoxMapper.findAllNotifications")
			.build();
	}

	@Bean
	@StepScope
	public ItemProcessor<Event, Event> EventProcessor() {
		log.debug("-------------------------------- ItemProcessor started --------------------------------");
		return new ItemProcessor<Event, Event>() {
			@Override
			public Event process(Event event) throws Exception {

				NotificationRequestDto dto = NotificationCreatedEvent.convertEventToDto(event);
				firebaseMessageSender.sendNotification(dto);

				event.deleteEvent();
				return event;
			}
		};
	}

	@Bean
	@StepScope
	public MyBatisBatchItemWriter<Event> EventWriter() {
		log.debug("-------------------------------- ItemWriter started --------------------------------");
		return new MyBatisBatchItemWriterBuilder<Event>()
			.assertUpdates(false)
			.sqlSessionFactory(sqlSessionFactory)
			.statementId("com.rollingpaper.ggeujeogggeujeog.outbox.infrastructure.OutBoxMapper.updateEvent")
			.build();
	}

}
