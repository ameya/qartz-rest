package com.example.QuartzREST;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

/**
 * @author  Ameya Shetti
 */
@Slf4j
@Service
public class QartzRestService {

    @Autowired private Scheduler scheduler;

    public void scheduleJobWithCronTrigger(String jobId, String cronExpresson) throws SchedulerException {
        JobDetail jobDetail = this.buildJobDetail(jobId);
        scheduler.scheduleJob(jobDetail,buildJobCronTrigger(jobId,jobDetail,cronExpresson));
        scheduler.start();
    }

    public void scheduleJobWithSimpleTrigger(String jobId) throws SchedulerException {
        JobDetail jobDetail = this.buildJobDetail(jobId);
        scheduler.scheduleJob(jobDetail,buildJobSimpleTrigger(jobId,jobDetail));
        scheduler.start();
    }

    private JobDetail buildJobDetail(String jobId) {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put(QartzRestConstants.KEY_JOB_ID, jobId);

        return JobBuilder.newJob(QartzRestJob.class)
                .withIdentity(QartzRestConstants.BATCHJOB+jobId, QartzRestConstants.GROUP1)
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildJobSimpleTrigger(String jobId, JobDetail jobDetail) {

        return TriggerBuilder.newTrigger()
                .withIdentity(QartzRestConstants.BATCH_TRIGGER+jobId, QartzRestConstants.GROUP1)
                .forJob(jobDetail)
                .startAt(Date.from(Instant.now().plusSeconds(30)))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }

    private Trigger buildJobCronTrigger(String jobId, JobDetail jobDetail, String cronExpression) {
        log.debug("cron expression used : {}",cronExpression);

        return TriggerBuilder.newTrigger()
                .withIdentity(QartzRestConstants.BATCH_TRIGGER+jobId, QartzRestConstants.GROUP1)
                .forJob(jobDetail)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
    }
}
