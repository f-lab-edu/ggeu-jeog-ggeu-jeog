package com.rollingpaper.ggeujeogggeujeog.outbox.infrastructure;

import org.joda.time.LocalDateTime;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@DisallowConcurrentExecution
@Slf4j
public class NotificationSendingJob extends QuartzJobBean {

	private final JobLauncher jobLauncher;
	private final JobLocator jobLocator;

	@SneakyThrows
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.debug("-------------------------------- quartz job started --------------------------------");
		JobParameters jobParameters = new JobParametersBuilder()
				.addDate("Date", LocalDateTime.now().toDate())
				.toJobParameters();

		jobLauncher.run(jobLocator.getJob("notificationSendingJob"), jobParameters);
	}
}
