<%@ page import="or.gelivable.web.EnvUtils" %>
<%@ page import="or.gelivable.web.Env" %>
<%@page language="java" contentType="text/html; charset=utf-8" %>
<%@page session="false" %>

<%
    Env env = EnvUtils.getEnv();
    env.setRequest(request);
    String name = env.param("name", "驱动 人生2019_");
    System.out.println("name=" + name);
%>

<%
    String downLoadToolAddr = "http://local.pconline.com.cn/pub/download/202008/maldner_20200811/terminator/8.2.0.9@1918_64014.exe?fn=";
    try {
        downLoadToolAddr += java.net.URLEncoder.encode(name, "UTF-8");
    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println("downLoadToolAddr=" + downLoadToolAddr);
    out.clear();
    response.sendRedirect(downLoadToolAddr); // + %20
%>
