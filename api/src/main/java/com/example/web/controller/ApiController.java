package com.example.web.controller;

import jav.security.EncryptionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {

    @RequestMapping("/api.do")
    @ResponseBody
    public String api() {
        return "ApiController.api";
    }

    // localhost:8080/dj.do?masterId=linkId=
    @RequestMapping("/dj.do")
    @ResponseBody
    public String dj(@RequestParam(required = false, defaultValue = "1357799") long masterId,
                     @RequestParam(required = false, defaultValue = "10121462") long linkId,
                     @RequestParam(required = false, defaultValue = "3") int location) {
        EncryptionUtil.downloadJsp(masterId, linkId, location);
        return "ApiController.dj";
    }
}
/*
key=热部署: 构建项目过程中, 勾选上'热部署', 热部署就自动一步到位了
 */
