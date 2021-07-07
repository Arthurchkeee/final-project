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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<c:import url="main.jsp"/>
<div class="container">
    <a class="btn btn-outline-dark m-lg-3" href="librarian" >Orders</a>
    <a class="btn btn-outline-dark m-lg-3" href="returning" disabled="true">Return</a>

    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <br>
        <table id="books" class="table table-bordered table-striped">
            <thead class="thread-light">
            <tr>
                <th>Book id</th>
                <th>Book name</th>
                <th>Author</th>
                <th>Username</th>
                <th>Canceled</th>
                <th>Return</th>
            </tr>
            </thead>
            <tbody id="bookTable">
            <c:forEach var="subscription" items="${subscriptions}">
                <c:if test="${subscription.books.status eq 'RETURNING_ROOM' || subscription.books.status eq 'RETURNING_SUBSCRIPTION'}">
                    <tr>
                        <td>${subscription.books.id}</td>
                        <td>${subscription.books.name}</td>
                        <td>${subscription.books.author}</td>
                        <td>${subscription.user.name}</td>
                        <form method="post" action="canceledReturning">
                            <input type="hidden" name="book_id" value=${subscription.books.id} />
                            <input type="hidden" name="status" value=${subscription.books.status} />
                            <td id="canceled">
                                <input type="submit" class="btn btn-outline-danger" value="CANCELED">
                            </td>
                        </form>
                        <form method="post" action="returning">
                            <input type="hidden" name="id" value=${subscription.id} />
                            <input type="hidden" name="book_id" value=${subscription.books.id} />
                            <td id="action">
                                <input type="submit" class="btn btn-outline-success" value="RETURN">
                            </td>
                        </form>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>

</div>
<script>
    $(document).ready(function(){
        $("#myInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#bookTable tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</body>
</html>