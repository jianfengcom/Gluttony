package com.example.sbjsp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class JspController {

    // key=请求转发的三种方法
    // ps: 请求转发是一次请求，地址栏不会发生变化
    /**
     * 1. 请求转发经过视图解析器
     * @return
     */
    @RequestMapping("/hello.do")
    public String hello() {
        return "hello";
    }

    /**
     * 2.请求转发不经过视图解析器
     * forward: 表示不再经过视图解析
     * ps: 由于不经过视图解析器，所以路径需要自己拼接前后缀
     * @param request
     * @return
     */
    @RequestMapping("/forward.do")
    public String forward(HttpServletRequest request) {
        request.setAttribute("msg", "贝丝·哈蒙");
        return "forward:/WEB-INF/jsp/forward.jsp";
    }

    /**
     * 3. 请求转发传统的方式——原生Servlet
     * ps: 当使用请求转发时，新的servlet的requset和resp是来自于上一个的servlet的requset和resp
     * @param request
     * @param response
     */
    @RequestMapping("/dispatcher.do")
    public void dispatcher(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("msg", "Anya Taylor-Joy");
            request.getRequestDispatcher("/WEB-INF/jsp/dispatcher.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
