package com.rollingpaper.ggeujeogggeujeog.common.config;

import org.quartz.JobDetail;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import com.rollingpaper.ggeujeogggeujeog.event.infrastructure.MailSendingJob;
import com.rollingpaper.ggeujeogggeujeog.event.infrastructure.NotificationSendingJob;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class QuartzConfig {

	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
		return jobRegistryBeanPostProcessor;
	}

	@Bean(name = "notification-job")
	public JobDetailFactoryBean jobDetailFactoryBeanNotification() {
		JobDetailFactoryBean jobDetailFactoryBean
			= new JobDetailFactoryBean();
		jobDetailFactoryBean.setJobClass(NotificationSendingJob.class);
		jobDetailFactoryBean.setGroup("Notification-sending-group");
		jobDetailFactoryBean.setName("Notification-sending-job");
		jobDetailFactoryBean.setDurability(true);
		return jobDetailFactoryBean;
	}

	@Bean(name = "mailSending-job")
	public JobDetailFactoryBean jobDetailFactoryBeanMailSending() {
		JobDetailFactoryBean jobDetailFactoryBean
			= new JobDetailFactoryBean();
		jobDetailFactoryBean.setJobClass(MailSendingJob.class);
		jobDetailFactoryBean.setGroup("Mail-sending-group");
		jobDetailFactoryBean.setName("Mail-sending-job");
		jobDetailFactoryBean.setDurability(true);
		return jobDetailFactoryBean;
	}

	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBeanNotification(
		@Qualifier("notification-job") JobDetail jobDetail
	) {
		CronTriggerFactoryBean cronTriggerFactoryBean
			= new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(jobDetail);
		cronTriggerFactoryBean.setGroup("Notification-sending-group");
		cronTriggerFactoryBean.setName("Notification-sending-per-1-minute");
		cronTriggerFactoryBean.setCronExpression("0 0/1 * 1/1 * ? *");
		return cronTriggerFactoryBean;
	}

	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBeanMailSending(
		@Qualifier("mailSending-job") JobDetail jobDetail
	) {
		CronTriggerFactoryBean cronTriggerFactoryBean
			= new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(jobDetail);
		cronTriggerFactoryBean.setGroup("Mail-sending-group");
		cronTriggerFactoryBean.setName("Mail-sending-job-per-30-seconds");
		cronTriggerFactoryBean.setCronExpression("0/30 * * * * ? *");
		return cronTriggerFactoryBean;
	}
}
