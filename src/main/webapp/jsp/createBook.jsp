<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<c:import url="entities.jsp"/>
<div class="container">
    <div class="input-group mb-3">
        <form class="mx-auto" method="post" action="createBook">
            <span class="input-group-text" id="basic-addon1"><fmt:message key="addBook.name"/></span>
            <input type="text" name="name" class="form-control" placeholder="<fmt:message key="addBook.name"/>"
                   aria-label="<fmt:message key="addBook.name"/>" aria-describedby="basic-addon1" required minlength="1"
                   maxlength="100" pattern="^[A-Za-zА-Яа-яЁё0-9\s-–()]{1,100}">
            <br>
            <span class="input-group-text" id="basic-addon2"><fmt:message key="addBook.author"/></span>
            <input type="text" name="author" class="form-control" placeholder="<fmt:message key="addBook.author"/>"
                   aria-label="<fmt:message key="addBook.author"/>" aria-describedby="basic-addon2" required
                   minlength="1" maxlength="100" pattern="^[A-Za-zА-Яа-яЁё0-9\s-–]{1,100}">
            <br>
            <span class="input-group-text" id="basic-addon3"><fmt:message key="addBook.description"/></span>
            <input type="text" name="description" class="form-control"
                   placeholder="<fmt:message key="addBook.description"/>"
                   aria-label="<fmt:message key="addBook.description"/>" aria-describedby="basic-addon3"
                   pattern="^[A-Za-zА-Яа-яЁё0-9\s().,–-?!/«»]+$">
            <br>
            <span class="input-group-text" id="basic-addon4"><fmt:message key="addBook.imageAddress"/></span>
            <input type="text" name="image" class="form-control" placeholder="<fmt:message key="addBook.imageAddress"/>"
                   aria-label="<fmt:message key="addBook.imageAddress"/>" aria-describedby="basic-addon4" required
                   minlength="5" maxlength="100" pattern="^[A-Za-zА-Яа-яЁё0-9\s/.-–-()]{5,100}">
            <br>
            <span class="input-group-text" id="basic-select"><fmt:message key="addBook.genre"/></span>
            <select class="form-select" name="genre" aria-describedby="basic-select">
                <option value="DETECTIVE">DETECTIVE</option>
                <option value="HORROR">HORROR</option>
                <option value="COMEDY">COMEDY</option>
                <option value="DRAMA" selected>DRAMA</option>
                <option value="ADULT">ADULT</option>
                <option value="ADULT">ADVENTURE</option>
                <option value="ADULT">CLASSIC</option>
                <option value="ADULT">COMICS</option>
                <option value="ADULT">FANTASTIC</option>
                <option value="ADULT">FANTASY</option>
            </select>
            <input type="submit" class="btn btn-outline-success" value="<fmt:message key="addBook.button"/>">
        </form>
    </div>
</div>
</body>
</html>
