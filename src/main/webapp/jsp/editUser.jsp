<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 05.07.2021
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<c:import url="entities.jsp"/>
<div class="container">
    <div class="input-group mb-3">
        <form class="mx-auto" method="get" action="editUser">
            <input type="hidden" name="id" value=${id}/>
            <span class="input-group-text" id="basic-addon1"><fmt:message key="addUser.name"/></span>
            <input type="text" name="user_name" class="form-control" placeholder="<fmt:message key="addBook.name"/>"
                   aria-label="<fmt:message key="addUser.name"/>" aria-describedby="basic-addon1" value="${name}"
                   required minlength="6" maxlength="25" pattern="^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\d.-]{0,25}$">
            <br>
            <span class="input-group-text" id="basic-addon2"><fmt:message key="addUser.password"/></span>
            <input type="text" name="password" class="form-control" placeholder="<fmt:message key="addUser.password"/>"
                   aria-label="<fmt:message key="addUser.password"/>" aria-describedby="basic-addon2" required
                   minlength="5" maxlength="50" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{5,}$">
            <br>
            <span class="input-group-text" id="basic-select"><fmt:message key="addUser.role"/></span>
            <select class="form-select" name="role" aria-describedby="basic-select">
                <option value="ADMIN"><fmt:message key="role.administrator"/></option>
                <option value="LIBRARIAN"><fmt:message key="role.librarian"/></option>
                <option value="READER" selected><fmt:message key="role.reader"/></option>
            </select>
            <input type="submit" class="btn btn-outline-success" value="<fmt:message key="users.edit"/>">
        </form>
    </div>
</div>
</body>
</html>
