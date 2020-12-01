<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // 通过window.open打开当前页或者直接输入地址, 为null
    String referer = request.getHeader("referer"); // 获取来访者地址
    System.out.println("referer=" + referer);

    /* key=404

    try {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    } catch (IOException e) {
        System.out.println(2333);
        e.printStackTrace();
    }*/
%>

<html>
<head>
    <title>Title</title>
</head>
<body>referer

</body>
</html>
