package br.com.dantas.springbatchv5.config;

import br.com.dantas.springbatchv5.job.EmailSalesScheduleJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail quartzJobDetail() {
        return JobBuilder
                .newJob(EmailSalesScheduleJob.class)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger quartzTrigger(JobDetail jobDetail) {

        SimpleScheduleBuilder simpleScheduleBuilder =
                SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(60)
                        .withRepeatCount(2);

        return TriggerBuilder
                .newTrigger()
                .forJob(quartzJobDetail())
                .withSchedule(simpleScheduleBuilder)
                .build();
    }
}
