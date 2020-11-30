package com.example.sbjsp.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.Enumeration;

@Controller
public class JspController {
    @Value("${server.tomcat.prg-root}")
    private String prgRoot;

    // key=请求转发的三种方法
    // ps: 请求转发是一次请求，地址栏不会发生变化

    /**
     * 1. 请求转发经过视图解析器
     *
     * @return
     */
    @RequestMapping("/hello.do")
    public String hello() {
        return "forward";
    }

    /**
     * 2.请求转发不经过视图解析器
     * forward: 表示不再经过视图解析
     * ps: 由于不经过视图解析器，所以路径需要自己拼接前后缀
     *
     * @param request
     * @return
     */
    @RequestMapping("/forward.do")
    public String forward(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("fn", "嘿嘿嘿");
        printResponse(response);
        return "forward:/forward2.do";
    }

    @RequestMapping("/forward2.do")
    public String forward2(HttpServletRequest request, HttpServletResponse response) {
        printResponse(response);
        return "forward:/WEB-INF/jsp/forward.jsp";
    }

    /**
     * 3. 请求转发传统的方式——原生Servlet
     * ps: 当使用请求转发时，新的servlet的requset和resp是来自于上一个的servlet的requset和resp
     *
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

    /**
     * 重定向
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/redirect1.do")
    public String redirect1(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/redirect2.do";
    }
    @RequestMapping("/redirect2.do")
    public void redirect2(HttpServletRequest request, HttpServletResponse response) {
        try {
//            response.sendRedirect("/WEB-INF/jsp/dispatcher.jsp"); 错误
            response.sendRedirect("/jsp_redirect.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printRequest(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            //根据名称获取请求头的值
            String value = request.getHeader(name);
            System.out.println(name + "--" + value);
        }
    }
    public void printResponse(HttpServletResponse response) {
        Collection<String> headerNames = response.getHeaderNames();
        System.out.println("headerNames=" + headerNames);
        System.out.println("isEmpty=" + headerNames.isEmpty());

        for (String name : headerNames) {
            String value = response.getHeader(name);
            System.out.println(name + "--" + value);
        }
    }
}
