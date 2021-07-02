<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 02.07.2021
  Time: 17:10
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
    <div>
        <table id="books" border="1" cellpadding="5">
            <tr>
                <th>Book id</th>
                <th>Book name</th>
                <th>Author</th>
                <th>Username</th>
                <th>Return</th>
            </tr>
            <c:forEach var="subscription" items="${subscriptions}">
                <c:if test="${subscription.books.status eq 'RETURNING_ROOM' || subscription.books.status eq 'RETURNING_SUBSCRIPTION'}">
                    <tr>
                        <td>${subscription.books.id}</td>
                        <td>${subscription.books.name}</td>
                        <td>${subscription.books.author}</td>
                        <td>${subscription.user.name}</td>
                        <form method="get" action="returning">
                            <input type="hidden" name="id" value=${subscription.id} />
                            <input type="hidden" name="book_id" value=${subscription.books.id} />
                            <td id="action">
                                <input type="submit" value="return">
                            </td>
                        </form>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
