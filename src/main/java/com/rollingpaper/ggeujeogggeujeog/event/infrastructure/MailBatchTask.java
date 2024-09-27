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

import com.rollingpaper.ggeujeogggeujeog.authentication.domain.EmailMessage;
import com.rollingpaper.ggeujeogggeujeog.authentication.infrastructure.EmailSender;
import com.rollingpaper.ggeujeogggeujeog.event.domain.Event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class MailBatchTask {

	private static final String JOB_NAME = "mailSendingJob";
	private static final String STEP_NAME = "startMailStep";
	private static final String QUERY_ID =
		"com.rollingpaper.ggeujeogggeujeog.event.infrastructure.EventMapper.findAllMails";
	private static final String STATEMENT_ID =
		"com.rollingpaper.ggeujeogggeujeog.event.infrastructure.EventMapper.deleteEvent";

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final SqlSessionFactory sqlSessionFactory;
	private final EmailSender emailSender;

	@Bean
	public Job mailSendingJob() {
		Job mailSendingJob = jobBuilderFactory.get(JOB_NAME)
			.start(startMailStep())
			.build();
		log.info("Batch Task : mailSendingJob created.");
		return mailSendingJob;
	}

	@Bean
	@JobScope
	public Step startMailStep() {
		TaskletStep taskletStep = stepBuilderFactory.get(STEP_NAME)
			.<Event, Event>chunk(100)
			.reader(MailSendingEventReader())
			.processor(MailSendingEventProcessor())
			.writer(MailSendingEventWriter())
			.build();
		log.info("Batch Task : MailSending taskletStep created.");
		return taskletStep;
	}

	@Bean
	@StepScope
	public MyBatisPagingItemReader<Event> MailSendingEventReader() {
		MyBatisPagingItemReader<Event> pagingItemReader = new MyBatisPagingItemReaderBuilder<Event>()
			.pageSize(100)
			.sqlSessionFactory(sqlSessionFactory)
			.queryId(QUERY_ID)
			.build();
		log.info("Batch Task : MailSending pagingItemReader created");
		return pagingItemReader;
	}

	@Bean
	@StepScope
	public ItemProcessor<Event, Event> MailSendingEventProcessor() {
		ItemProcessor<Event, Event> itemProcessor = new ItemProcessor<>() {
			@Override
			public Event process(Event event) throws Exception {

				EmailMessage message = EmailMessage.convertEventToMessage(event);
				emailSender.sendEmail(message);

				event.deleteEvent();
				log.info("Batch Task : MailSending event {} has been deleted.", event.getAggregateId());
				return event;
			}
		};
		return itemProcessor;
	}

	@Bean
	@StepScope
	public MyBatisBatchItemWriter<Event> MailSendingEventWriter() {
		MyBatisBatchItemWriter<Event> itemWriter = new MyBatisBatchItemWriterBuilder<Event>()
			.assertUpdates(false)
			.sqlSessionFactory(sqlSessionFactory)
			.statementId(STATEMENT_ID)
			.build();
		log.info("Batch Task : MailSending itemWriter created");
		return itemWriter;
	}
}
