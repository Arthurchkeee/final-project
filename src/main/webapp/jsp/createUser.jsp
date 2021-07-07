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
<c:import url="entities.jsp"/>
<div class="container">
    <div class="input-group mb-3">
<form method="post" action="createUser">
    <span class="input-group-text" id="basic-addon1">Name</span>
    <input type="text" name="name" class="form-control" placeholder="Name"  aria-label="Name" aria-describedby="basic-addon1" value=${name} >
    <span class="input-group-text" id="basic-addon2">Password</span>
    <input type="text" name="password" class="form-control" placeholder="Password" aria-label="Password" aria-describedby="basic-addon2"  >

    <br>
    <span class="input-group-text" id="basic-select">Role</span>
    <select class="form-select" name="role" aria-describedby="basic-select">
        <option value="ADMIN">Admin</option>
        <option value="LIBRARIAN">Librarian</option>
        <option value="READER" selected>Reader</option>
    </select>
    <input type="submit" class="btn btn-outline-success" value="CREATE">
</form>
</div>
</div>
</body>
</html>
