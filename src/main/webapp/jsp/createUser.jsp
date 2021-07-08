<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <span class="input-group-text" id="basic-addon1"><fmt:message key="addUser.name"/></span>
    <input type="text" name="name" class="form-control" placeholder="<fmt:message key="addUser.name"/>"  aria-label="<fmt:message key="addUser.name"/>" aria-describedby="basic-addon1" value=${name} >
    <br>
    <span class="input-group-text" id="basic-addon2"><fmt:message key="addUser.password"/></span>
    <input type="text" name="password" class="form-control" placeholder="<fmt:message key="addUser.password"/>" aria-label="<fmt:message key="addUser.password"/>" aria-describedby="basic-addon2"  >
    <br>
    <span class="input-group-text" id="basic-select"><fmt:message key="addUser.role"/></span>
    <select class="form-select" name="role" aria-describedby="basic-select">
        <option value="ADMIN"><fmt:message key="role.administrator"/></option>
        <option value="LIBRARIAN"><fmt:message key="role.librarian"/></option>
        <option value="READER" selected><fmt:message key="role.reader"/></option>
    </select>
    <input type="submit" class="btn btn-outline-success" value="<fmt:message key="addUser.button"/>">
</form>
</div>
</div>
</body>
</html>
