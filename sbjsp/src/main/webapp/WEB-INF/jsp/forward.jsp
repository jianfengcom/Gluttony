<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>forward

<%--木有就真的木有--%>
${msg}

<%--木有就显示null--%>
<%=request.getAttribute("msg")%>

<%=response.getHeader("fn")%>

</body>
</html>
