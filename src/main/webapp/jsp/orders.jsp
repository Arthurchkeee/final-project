<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 06.07.2021
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<c:import url="main.jsp"/>
<div class="container">
    <a class="btn btn-outline-dark " href="${pageContext.request.contextPath}/canceledReturning"><fmt:message
            key="myBooks.returnOrders"/></a>

    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <br>
    <table id="books" class="table table-bordered table-striped">
        <thead class="thread-light">
        <tr>
            <th>№</th>
            <th><fmt:message key="catalog.book"/></th>
            <th><fmt:message key="catalog.author"/></th>
            <th><fmt:message key="myBooks.cancel"/></th>
        </tr>
        </thead>
        <tbody id="bookTable">
        <c:forEach var="subscription" items="${subscriptions}">
            <tr>
                <td>${subscription.books.id}</td>
                <td>${subscription.books.name}</td>
                <td>${subscription.books.author}</td>
                <form method="post" action="canceledOrder">
                    <input type="hidden" name="book_id" value="${subscription.books.id}"/>
                    <input type="hidden" name="id" value="${subscription.id}"/>
                    <td id="action">
                        <input type="submit" class="btn btn-outline-danger"
                               value="<fmt:message key="myBooks.cancel"/> ">
                    </td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav aria-label="Page navigation example">
        <div class="btn-group mx-auto" role="group" aria-label="Basic example">
            <form class="mx-auto" method="get" action="canceledOrder">
                <c:forEach var="count" begin="1" end="${counts}">
                    <button type="submit" class="btn btn-outline-primary" name="page" value="${count}">${count}</button>
                </c:forEach>
            </form>

        </div>
    </nav>

</div>
</body>
</html>
