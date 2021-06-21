<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 25.05.2021
  Time: 09:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-color:DodgerBlue;">
<p style="color: tomato"></p>
<form method="post" action="${pageContext.request.contextPath}/controller?command=login">
    <input name="login">
    <input name="password">

    <input type="submit" value="sdss">

</form>
</body>
</html>
