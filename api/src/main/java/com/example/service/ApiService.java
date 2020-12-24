package com.example.service;

import or.springframework.context.SendEmailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author
 * @Date 2020/12/24
 * @Version 1.0
 */

@Service
public class ApiService {
    @Autowired
    private ApplicationContext ctx;

    public void sendEmail(String content) {
        ApplicationEvent event = new SendEmailEvent(this, content);
        ctx.publishEvent(event);
        System.out.println("已publishEvent:" + content);
    }

}
