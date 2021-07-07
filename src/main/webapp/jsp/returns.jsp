<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 06.07.2021
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<c:import url="main.jsp"/>
<div class="container">
    <a class="btn btn-outline-dark " href="${pageContext.request.contextPath}/canceledOrder">Orders</a>

    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <br>
    <table id="books" class="table table-bordered table-striped">
        <thead class="thread-light">
        <tr>
            <th>Book id</th>
            <th>Book name</th>
            <th>Author</th>
            <th>Canceled</th>
        </tr>
        </thead>
        <tbody id="bookTable">
        <c:forEach var="subscription" items="${subscriptions}">
            <c:if test="${subscription.books.status eq 'RETURNING_SUBSCRIPTION' || subscription.books.status eq 'RETURNING_ROOM'}">
                <tr>
                    <td>${subscription.books.id}</td>
                    <td>${subscription.books.name}</td>
                    <td>${subscription.books.author}</td>
                    <form method="post" action="canceledReturning">
                        <input type="hidden" name="book_id" value=${subscription.books.id} />
                        <input type="hidden" name="status" value=${subscription.books.status} />
                        <td id="action">
                            <input type="submit" class="btn btn-outline-danger" value="CANCELED">
                        </td>
                    </form>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>
