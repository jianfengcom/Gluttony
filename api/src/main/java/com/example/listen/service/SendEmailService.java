package com.example.listen.service;

import or.springframework.context.SendEmailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
    @Autowired
    private ApplicationContext ctx;

    public void sendEmail(String content) {
        ApplicationEvent event = new SendEmailEvent(this, content);
        ctx.publishEvent(event);
        System.out.println("--send:" + content);
    }

}
