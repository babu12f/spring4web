package com.nrbswift.spring4web.config;

import com.nrbswift.spring4web.scheduler.MailJobService;
import com.nrbswift.spring4web.scheduler.MyJob;
import org.quartz.JobDataMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SchedulerConfiguration {

    @Bean
    public MailJobService mailJobService() {
        return new MailJobService();
    }

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(MyJob.class);

        Map<String, Object> mailJobServiceMap = new HashMap<>();
        mailJobServiceMap.put("mailJobService", mailJobService());

        jobDetailFactoryBean.setJobDataMap(new JobDataMap(mailJobServiceMap));
        jobDetailFactoryBean.setDurability(true);

        return jobDetailFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
        cronTriggerFactoryBean.setCronExpression("0/30 * * * * ?");

        return cronTriggerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobDetails(jobDetailFactoryBean().getObject());
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean().getObject());

        return schedulerFactoryBean;
    }
}
