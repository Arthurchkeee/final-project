<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 02.07.2021
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="main.jsp"/>
<form method="post" action="createBook">
    <input name="name">
    <input name="author"><br>
    <input type="radio" id="horror" name="genre" value="HORROR">
    <label for="horror">HORROR</label><br>
    <input type="radio" id="comedy" name="genre" value="COMEDY">
    <label for="comedy">COMEDY</label><br>
    <input type="radio" id="drama" name="genre" value="DRAMA">
    <label for="drama">DRAMA</label><br>
    <input type="radio" id="detective" name="genre" value="DETECTIVE">
    <label for="detective">DETECTIVE</label><br>
    <input type="radio" id="adult" name="genre" value="ADULT">
    <label for="adult">ADULT</label><br>
    <input type="submit" value="create">
</form>
</body>
</html>
