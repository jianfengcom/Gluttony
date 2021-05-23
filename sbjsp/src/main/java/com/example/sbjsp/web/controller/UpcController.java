package com.example.sbjsp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UpcController {

    @RequestMapping("/upc.do")
    @ResponseBody
    public String upc(HttpServletRequest request) {
        String[] covers = request.getParameterValues("cover");
        for (String cover : covers) {
            System.out.println(cover);
        }
        return "棒!";
    }

}
