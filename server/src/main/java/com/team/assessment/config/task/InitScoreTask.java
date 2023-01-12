package com.team.assessment.config.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class InitScoreTask {
    @Scheduled(cron = "0 0 1 1 * ?")
    private void initScore() {
        // TODO
    }
}
