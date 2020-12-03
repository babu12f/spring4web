package com.nrbswift.spring4web.scheduler;

import com.nrbswift.spring4web.utils.EmailUtil;

import java.util.Date;

public class MailJobService {

    public void send() {
        System.out.println("Job1 --->>> Time is " + new Date());
        EmailUtil.sendEmail("babugenerous@gmail.com","Email Testing Subject1", "Email Testing Body1");
    }

}
