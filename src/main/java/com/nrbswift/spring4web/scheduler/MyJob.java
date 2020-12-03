package com.nrbswift.spring4web.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;


public class MyJob extends QuartzJobBean {
    private MailJobService mailJobService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        mailJobService.send();
    }

    public MailJobService getMailJobService() {
        return mailJobService;
    }

    public void setMailJobService(MailJobService mailJobService) {
        this.mailJobService = mailJobService;
    }
}
