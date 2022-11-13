package com.rollingpaper.ggeujeogggeujeog.integration.outbox;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.testcontainers.shaded.org.apache.commons.lang3.time.DateFormatUtils;

import com.rollingpaper.ggeujeogggeujeog.common.config.AbstractContainerBaseTest;
import com.rollingpaper.ggeujeogggeujeog.outbox.domain.Event;

@SpringBatchTest
public class OutboxIntegrationTest extends AbstractContainerBaseTest {

	private static int DELETED_FLAG = 1;
	private static String TRIGGER_NAME = "Notification-sending-per-1-minute";
	private static String TRIGGER_GROUP = "Notification-sending-group";
	private static final String DATE_PATTERN = "yyyy/MM/dd hh:mm";

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private Scheduler scheduler;

	@Test
	@DisplayName("알림 배치 작업은 1분 간격으로 진행된다.")
	void sendMessageWithCronPerMinute() throws SchedulerException {
		//given
		Trigger trigger = findTrigger(TRIGGER_NAME, TRIGGER_GROUP);

		//then
		assertSchedule(LocalDateTime.now().toDate(), trigger, DATE_PATTERN);
	}

	@Test
	@DisplayName("배치 작업은 100개를 읽어 상태를 변경하고 DB에 저장한다.")
	void sendMessageThenUpdateEvent() throws Exception {
		//given
		JobParameters jobParameters = new JobParametersBuilder()
			.addDate("Date", LocalDateTime.now().toDate())
			.toJobParameters();

		//when
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

		//then
		assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
		assertThat(findAllNotificationEvent()).hasSize(100);
	}

	private List<Event> findAllNotificationEvent() {
		List<Event> result = jdbcTemplate.query(
			"SELECT * FROM outbox WHERE deleted = ?",
			notificationEventMapper(),
			DELETED_FLAG
		);
		return result;
	}

	private RowMapper<Event> notificationEventMapper() {
		return ((rs, rowNum) -> {
			Event event = new Event();
			event.setId(rs.getLong("id"));
			event.setDeleted(rs.getBoolean("deleted"));
			return event;
		});
	}

	private Trigger findTrigger(String name, String group) throws SchedulerException {
		return scheduler.getTrigger(TriggerKey.triggerKey(name, group));
	}

	private void assertSchedule(Date current, Trigger trigger, String datePattern) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 1);
		Date nextMinute = now.getTime();

		String triggerNextFireTime = convertDateToString(trigger.getNextFireTime(), datePattern);
		String nextMinuteTime = convertDateToString(nextMinute, datePattern);

		assertEquals(triggerNextFireTime, nextMinuteTime);
	}

	private String convertDateToString(Date time, String datePattern) {
		return DateFormatUtils.format(time, DATE_PATTERN);
	}

}
