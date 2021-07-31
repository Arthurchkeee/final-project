<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Title</title>
</head>
<body>
<c:import url="main.jsp"/>

    <div class="container">
    <br>

        <input class="form-control" id="myInput" type="text" placeholder="Search..">
        <br>
        <table class="table table-bordered table-striped" >
            <thead class="thread-light">
            <tr>
                <th>№</th>
                <th><fmt:message key="catalog.book"/></th>
                <th><fmt:message key="catalog.author"/></th>
                <th><fmt:message key="catalog.genre"/></th>
                <th><fmt:message key="catalog.more"/></th>
            </tr>
            </thead>
            <tbody id="bookTable">
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.genre}</td>
                    <form method="post" action="order">
                        <input type="hidden" name="id" value=${book.id} />
                        <td>
                        <input type="submit" class="btn btn-outline-primary" value="<fmt:message key="catalog.more"/>">
                    </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <form method="get" action="catalog">
                    <c:forEach var="count" begin="1" end="${counts}">


                    <form method="get" action="catalog">
                        <li  class="page-item"><input type="submit" class="page-link" name="page" value="${count}"/></li>


                        </c:forEach>
                    </form>
            </ul>
        </nav>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
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
