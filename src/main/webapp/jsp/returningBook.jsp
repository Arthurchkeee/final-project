<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<c:import url="main.jsp"/>
<div class="container">
    <a class="btn btn-outline-dark m-lg-3" href="librarian"><fmt:message key="orders.order"/></a>
    <a class="btn btn-outline-dark m-lg-3" href="returning" disabled="true"><fmt:message key="orders.return"/></a>
    <br>
    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <br>
    <table id="books" class="table table-bordered table-striped">
        <thead class="thread-light">
        <tr>
            <th>№</th>
            <th><fmt:message key="orders.bookName"/></th>
            <th><fmt:message key="orders.author"/></th>
            <th><fmt:message key="orders.username"/></th>
            <th><fmt:message key="orders.access"/></th>
            <th><fmt:message key="myBooks.cancel"/></th>
        </tr>
        </thead>
        <tbody id="bookTable">
        <c:forEach var="subscription" items="${subscriptions}">
            <tr>
                <td>${subscription.books.id}</td>
                <td>${subscription.books.name}</td>
                <td>${subscription.books.author}</td>
                <td>${subscription.user.name}</td>

                <form method="post" action="returning">
                    <input type="hidden" name="id" value=${subscription.id}/>
                    <input type="hidden" name="book_id" value=${subscription.books.id}/>
                    <input type="hidden" name="action" value="return"/>
                    <td id="action">
                        <input type="submit" class="btn btn-outline-success" value="<fmt:message key="orders.return"/>">
                    </td>
                </form>
                <form method="post" action="returning">
                    <input type="hidden" name="book_id" value=${subscription.books.id}/>
                    <input type="hidden" name="status" value=${subscription.books.status}/>
                    <input type="hidden" name="action" value="cancel"/>
                    <td>
                        <input type="submit" class="btn btn-outline-danger" value="<fmt:message key="myBooks.cancel"/>">
                    </td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#bookTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</body>
</html>
