package com.example.listen.config;

import com.example.listen.service.SendEmailService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan(basePackages = {"or.springframework.context", "com.example"})
@EnableAsync // 允许异步
public class SendEmailConfig {

    /**
     * ##: 监听事件实现
     *
     * @Description 监听事件, 允许异步并开启异步=SendEmailListener.onApplicationEvent()方法不用处理完一个事件对象才能处理一个事件对象
     * @Author
     * @Date
     * @From
     * @Function
     * @Version 1.0
     */
    public static void main(String[] args) {
        // AnnotationConfigApplicationContext可以实现 基于Java的配置类(EventConfig)加载Spring的应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SendEmailConfig.class);
        SendEmailService service = context.getBean(SendEmailService.class);

        // 邮件内容
        service.sendEmail("邮件1:有内鬼");
        service.sendEmail("邮件2:按原计划执行");
    }

}
