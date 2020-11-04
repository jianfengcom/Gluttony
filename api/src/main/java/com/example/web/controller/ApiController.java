package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {

    @RequestMapping("/api.do")
    @ResponseBody
    public String api() {
        return "ApiController.api";
    }
}
/*
key=热部署: 构建项目过程中, 勾选上'热部署', 热部署就自动一步到位了
 */
