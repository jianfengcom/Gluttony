package com.example.sbjsp.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @Description:
 * @Author
 * @Date 2020/12/1
 * @Version 1.0
 */

@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        // key=404
        ErrorPage errPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
        registry.addErrorPages(errPage404);
    }
}
