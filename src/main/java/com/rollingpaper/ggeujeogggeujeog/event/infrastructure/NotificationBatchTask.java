package com.rollingpaper.ggeujeogggeujeog.event.infrastructure;

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
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rollingpaper.ggeujeogggeujeog.event.domain.Event;
import com.rollingpaper.ggeujeogggeujeog.notification.domain.MessageSender;
import com.rollingpaper.ggeujeogggeujeog.notification.infrastructure.dto.NotificationRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class NotificationBatchTask {

	private static final String JOB_NAME = "notificationSendingJob";
	private static final String STEP_NAME = "startNotificationStep";
	private static final String QUERY_ID =
		"com.rollingpaper.ggeujeogggeujeog.event.infrastructure.EventMapper.findAllNotifications";
	private static final String STATEMENT_ID =
		"com.rollingpaper.ggeujeogggeujeog.event.infrastructure.EventMapper.deleteEvent";

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final SqlSessionFactory sqlSessionFactory;
	private final MessageSender messageSender;

	@Bean
	public Job notificationSendingJob() {
		Job notificationSendingJob = jobBuilderFactory.get(JOB_NAME)
			.start(startNotificationStep())
			.build();
		log.info("Batch Task : notificationSendingJob created.");
		return notificationSendingJob;
	}

	@Bean
	@JobScope
	public Step startNotificationStep() {
		TaskletStep taskletStep = stepBuilderFactory.get(STEP_NAME)
			.<Event, Event>chunk(100)
			.reader(NotificationEventReader())
			.processor(NotificationEventProcessor())
			.writer(NotificationEventWriter())
			.build();
		log.info("Batch Task : Notification taskletStep created.");
		return taskletStep;
	}

	@Bean
	@StepScope
	public MyBatisPagingItemReader<Event> NotificationEventReader() {
		MyBatisPagingItemReader<Event> pagingItemReader = new MyBatisPagingItemReaderBuilder<Event>()
			.pageSize(100)
			.sqlSessionFactory(sqlSessionFactory)
			.queryId(QUERY_ID)
			.build();
		log.info("Batch Task : Notification pagingItemReader created.");
		return pagingItemReader;
	}

	@Bean
	@StepScope
	public ItemProcessor<Event, Event> NotificationEventProcessor() {
		return new ItemProcessor<Event, Event>() {
			@Override
			public Event process(Event event) throws Exception {

				NotificationRequestDto dto = NotificationRequestDto.from(event);
				messageSender.sendNotification(dto);

				event.deleteEvent();
				return event;
			}
		};
	}

	@Bean
	@StepScope
	public MyBatisBatchItemWriter<Event> NotificationEventWriter() {
		MyBatisBatchItemWriter<Event> itemWriter = new MyBatisBatchItemWriterBuilder<Event>()
			.assertUpdates(false)
			.sqlSessionFactory(sqlSessionFactory)
			.statementId(STATEMENT_ID)
			.build();
		log.info("Batch Task : Notification itemWriter created");
		return itemWriter;
	}
}
