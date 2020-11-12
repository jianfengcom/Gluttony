<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>dispatcher

<%--木有就真的木有--%>
${msg}

<%--木有就显示null--%>
<%=request.getAttribute("msg")%>

</body>
</html>
