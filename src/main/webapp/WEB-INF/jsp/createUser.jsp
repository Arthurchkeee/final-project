<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 02.07.2021
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="main.jsp"/>
<form method="post" action="createUser">
    <input name="username">
    <input name="password"><br>
    <input type="radio" id="admin" name="role" value="ADMIN">
    <label for="admin">ADMIN</label><br>
    <input type="radio" id="librarian" name="role" value="LIBRARIAN">
    <label for="librarian">LIBRARIAN</label><br>
    <input type="radio" id="user" name="role" value="USER">
    <label for="user">USER</label><br>
    <input type="submit" value="create">
</form>
</body>
</html>
