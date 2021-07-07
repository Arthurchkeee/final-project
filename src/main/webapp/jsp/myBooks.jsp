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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Title</title>
</head>
<body>
<c:import url="main.jsp"/>
<div class="container">
    <p class="h5">Your Books</p>

    <a class="btn btn-outline-dark " href="${pageContext.request.contextPath}/canceledOrder">Orders</a>


    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <br>
        <table id="books" class="table table-bordered table-striped">
            <thead class="thread-light">
            <tr>
                <th>Book id</th>
                <th>Book name</th>
                <th>Author</th>
                <th>Access</th>
            </tr>
            </thead>
            <tbody id="bookTable">
            <c:forEach var="subscription" items="${subscriptions}">
                <c:if test="${subscription.books.status eq 'SUBSCRIPTION' || subscription.books.status eq 'ROOM'}">
                    <tr>
                        <td>${subscription.books.id}</td>
                        <td>${subscription.books.name}</td>
                        <td>${subscription.books.author}</td>
                        <form method="post" action="myBooks">
                            <input type="hidden" name="id" value=${subscription.books.id} />
                            <input type="hidden" name="status" value=${subscription.books.status} />
                            <td id="action">
                                <input type="submit" class="btn btn-outline-danger" value="RETURN">
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
