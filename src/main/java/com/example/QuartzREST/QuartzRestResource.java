package com.example.QuartzREST;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class QuartzRestResource {

    @Autowired private QartzRestService qartzRestService;

    @SneakyThrows
    @GetMapping("/schedule/cron/job/{id}")
    public String scheduleCronJob(@PathVariable("id") String id) {
        qartzRestService.scheduleJobWithCronTrigger(id,"0/5 0 0 ? * * *");
        return "OK";
    }

    @SneakyThrows
    @GetMapping("/schedule/simple/job/{id}")
    public String scheduleSimpleJob(@PathVariable("id") String id) {
        qartzRestService.scheduleJobWithSimpleTrigger(id);
        return "OK";
    }
}
