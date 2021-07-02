<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 29.06.2021
  Time: 07:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="main.jsp"/>
<div>

    ${role}

    <div>
        <table id="books" border="1" cellpadding="5">
            <tr>
                <th>Id</th>
                <th>Book</th>
                <th>Author</th>
                <th>Genre</th>
                <th>Status</th>
            </tr>

            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.genre}</td>
                    <td>${book.status}</td>
                    <c:if test="${book.status eq 'FREE'}">
                    <form method="get" action="order">
                        <input type="hidden" name="id" value=${book.id} />
                    <td id="action">
                        <input type="radio" id="room" name="status" value="ROOM">
                        <input type="radio" id="css" name="status" value="SUBSCRIBE">
                        <input type="submit" value="ORDER">
                    </td>
                    </form>
                    </c:if>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>
</body>
</html>
