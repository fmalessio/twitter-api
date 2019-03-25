package com.fmalessio.twitterapi.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmalessio.twitterapi.quartz.jobs.TweetsQuartzJob;

@Service
public class TweetsServiceImpl implements TweetsService {

	@Autowired
	private Scheduler scheduler;

	private String TRIGGER_GROUP_NAME = "tweets-search-scheduler";
	private String JOB_GROUP_NAME = "tweets-jobs";
	private int INTERVAL_IN_SECONDS = 30;

	public void runScheduler() {
		JobDetail jobDetail = buildJobDetail();
		ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.systemDefault()).plusSeconds(30);
		Trigger trigger = buildJobTrigger(jobDetail, dateTime);
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	private JobDetail buildJobDetail() {
		JobDataMap jobDataMap = new JobDataMap();

		jobDataMap.put("number", 5);
		jobDataMap.put("value", "#boca");

		return JobBuilder.newJob(TweetsQuartzJob.class).withIdentity(UUID.randomUUID().toString(), JOB_GROUP_NAME)
				.withDescription("Testing Twitter").usingJobData(jobDataMap).storeDurably().build();
	}

	private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
		return TriggerBuilder.newTrigger().forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), TRIGGER_GROUP_NAME).withDescription("Search Tweets")
				.startAt(Date.from(startAt.toInstant())).withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(INTERVAL_IN_SECONDS).repeatForever())
				.build();
	}

	public void removeJob(String jobName) {
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
		JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
		try {
			Trigger trigger;
			trigger = (Trigger) scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}
			scheduler.pauseTrigger(triggerKey);
			scheduler.unscheduleJob(triggerKey);
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
