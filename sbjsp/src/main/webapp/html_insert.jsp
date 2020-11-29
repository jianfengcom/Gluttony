<%@ page import="or.gelivable.web.EnvUtils" %>
<%@ page import="or.gelivable.web.Env" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Env env = EnvUtils.getEnv();
    env.setRequest(request);
    String p = env.param("p", "");
    p = p
            .replaceAll("<", "&lt;")
            .replaceAll(">", "&gt;");
    request.setAttribute("p", p);
%>

<html>
<head>
    <title>Title</title>
</head>
<body>${p}
<%--<h1 style="color: crimson">法克鱿</h1>--%>

</body>
</html>
