<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>jsp_redirect

<%--木有就真的木有--%>
${msg}

<%--木有就显示null--%>
<%=request.getAttribute("msg")%>

<a href="referer.jsp">referer</a>

</body>
</html>
