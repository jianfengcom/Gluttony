<%@ page import="or.gelivable.web.EnvUtils" %>
<%@ page import="or.gelivable.web.Env" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Env env = EnvUtils.getEnv();
    env.setRequest(request);
    String p = env.param("p", "");
    /*
    key=XssFilter 过滤并重构request
    p = p
            .replaceAll("<", "&lt;")
            .replaceAll(">", "&gt;");*/
    request.setAttribute("p", p);
%>

<html>
<head>
    <title>Title</title>
</head>
<body>${p}
<%--http://localhost:81/html_insert.jsp?p=<script>alert(145)</script>--%>
<%--<script>alert(145)</script>--%>

</body>
</html>
