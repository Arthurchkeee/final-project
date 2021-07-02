<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 02.07.2021
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Your Books</p>
<c:import url="main.jsp"/>
<div>

    <div>
        <table id="books" border="1" cellpadding="5">
            <tr>
                <th>Book id</th>
                <th>Book name</th>
                <th>Author</th>
                <th>Access</th>
            </tr>
            <c:forEach var="subscription" items="${subscriptions}">
                <c:if test="${subscription.books.status eq 'SUBSCRIPTION' || subscription.books.status eq 'ROOM'}">
                    <tr>
                        <td>${subscription.books.id}</td>
                        <td>${subscription.books.name}</td>
                        <td>${subscription.books.author}</td>
                        <form method="get" action="myBooks">
                            <input type="hidden" name="id" value=${subscription.books.id} />
                            <input type="hidden" name="status" value=${subscription.books.status} />
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
