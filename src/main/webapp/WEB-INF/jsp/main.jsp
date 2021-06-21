<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 25.05.2021
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/controller?command=show_login">Sign in</a>
    <a href="${pageContext.request.contextPath}/controller?command=show_page">Show page</a>

    ${role}
</div>
</body>
</html>
