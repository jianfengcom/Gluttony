package com.example.sbjsp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {

    @RequestMapping("/hello.do")
    public String hello() {
        return "hello";
    }
}
