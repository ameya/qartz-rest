# qartz-rest
Simple Spring boot Qartz REST applicaiotn with dynamic job invocaiton.

Import application in you IDE , please check configure lombak for Intellij IDE if required.

Run QuartzRestApplication class

Hosted on port 8080

http://localhost:8080/api/schedule/simple/job/test1

check the logs

this triggers set to run after 30 seconds.

12:47:02.515 [http-nio-8080-exec-1] INFO  org.quartz.core.QuartzScheduler - Scheduler quartzScheduler_$_NON_CLUSTERED started.
12:47:02.515 [quartzScheduler_QuartzSchedulerThread] DEBUG o.quartz.core.QuartzSchedulerThread - batch acquisition of 1 triggers
12:47:32.521 [quartzScheduler_QuartzSchedulerThread] DEBUG o.quartz.core.QuartzSchedulerThread - batch acquisition of 0 triggers
12:47:32.521 [quartzScheduler_Worker-1] DEBUG org.quartz.core.JobRunShell - Calling execute on job GROUP1.BatchJob-test1
12:47:35.896 [quartzScheduler_Worker-1] DEBUG com.example.QuartzREST.QartzRestJob - Job with job id test1 is running...2021-02-27T12:47:35.890673700

http://localhost:8080/api/schedule/cron/job/test2

check the logs

this triggers set to run every 5 sec with cron epression 0/5 0 0 ? * * *

12:43:07.312 [http-nio-8080-exec-1] INFO  org.quartz.core.QuartzScheduler - Scheduler quartzScheduler_$_NON_CLUSTERED started.
12:43:07.312 [quartzScheduler_QuartzSchedulerThread] DEBUG o.quartz.core.QuartzSchedulerThread - batch acquisition of 0 triggers
