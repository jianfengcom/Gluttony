package com.example.config;

import com.example.service.ApiService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Description:
 * @Author
 * @Date 2020/12/24
 * @Version 1.0
 */

@Configuration
@ComponentScan(basePackages={"or.springframework.context", "com.example"})

// 想知道@EnableAsync注解的原理,可以查看 AsyncConfigurationSelector 类的源码
@EnableAsync
public class EventConfig {

    //key=异步Listener
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        // 用注入的形式完成事件发布
        ApiService apiService = context.getBean(ApiService.class);
        apiService.sendEmail("有内鬼");
        apiService.sendEmail("按原计划执行");
    }

}
