package com.example.web.controller;

import or.apache.commons.httpclient.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ApiController {

    @RequestMapping("/postMethod.do")
    @ResponseBody
    public String postMethod() {
        String url = "https://best.pconline.com.cn/intf/oneBuyer/getTopicList.jsp";
        Map<String, String> params = new HashMap<>();
        params.put("ids", "62,64");

        return HttpClientUtil.postMethod(url, params);
    }

    @RequestMapping("/api.do")
    @ResponseBody
    public String api() {
        return "ApiController.api";
    }
}
/*
key=热部署: 构建项目过程中, 勾选上'热部署', 热部署就自动一步到位了
 */
