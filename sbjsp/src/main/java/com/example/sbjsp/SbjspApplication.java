package com.example.sbjsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = {"javx.servlet.filter"})
public class SbjspApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbjspApplication.class, args);
    }

}
