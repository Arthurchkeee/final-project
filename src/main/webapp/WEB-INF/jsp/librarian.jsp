
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 30.06.2021
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <th>Book id</th>
                <th>Book name</th>
                <th>Author</th>
                <th>Username</th>
                <th>Access</th>
            </tr>
            <c:forEach var="subscription" items="${subscriptions}">
                <c:if test="${subscription.books.status eq 'ORDERED_ROOM' || subscription.books.status eq 'ORDERED_SUBSCRIPTION'}">
                ${subscription.id}
                <tr>
                    <td>${subscription.books.id}</td>
                    <td>${subscription.books.name}</td>
                    <td>${subscription.books.author}</td>
                    <td>${subscription.user.name}</td>
                    <form method="get" action="librarian">
                        <input type="hidden" name="id" value=${subscription.books.id} />
                        <input type="hidden" name="status" value=${subscription.books.status} />
                        <td id="action">
                            <input type="submit" value="access">
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
