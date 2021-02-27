package com.example.QuartzREST;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;

/**
 * @author  Ameya Shetti
 */
@Slf4j
public class QartzRestJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String jobId = jobExecutionContext.getJobDetail().getJobDataMap().getString(QartzRestConstants.KEY_JOB_ID);
        log.debug("Job with job id "+jobId+" is running..."+ LocalDateTime.now());
    }
}
