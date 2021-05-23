package com.example.sbjsp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UpcController {

    @RequestMapping("/upc.do")
    public String upc(HttpServletRequest request, Model model) {
        String[] covers = request.getParameterValues("cover");
        if (covers != null) {
            for (String cover : covers) {
                System.out.println(cover);
            }
        }
        List<String> picList = new ArrayList<>();
        picList.add("http://t-upc.pconline.com.cn/upcfiles/images/upload/upc/tx/pc_best/2105/24/c0/59513217_1621788504189.jpg");
        picList.add("http://t-upc.pconline.com.cn/upcfiles/images/upload/upc/tx/pc_best/2105/24/c0/59513218_1621788513293.jpg");
        model.addAttribute("picList", picList);
        return "forward:/upc.jsp";
    }

}
