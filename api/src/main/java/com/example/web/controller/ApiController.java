package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {

    @RequestMapping("/fuck.do")
    @ResponseBody
    public String fuck() {
        return null;
    }

}
