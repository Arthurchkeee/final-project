<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
</head>
<body>

<c:import url="main.jsp"/>
<div class="container">

    <br>

    <a class="btn btn-outline-dark " href="${pageContext.request.contextPath}/canceledOrder"><fmt:message key="myBooks.order"/></a>

<br>


    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <br>
        <table id="books" class="table table-bordered table-striped">
            <thead class="thread-light">
            <tr>
                <th>№</th>
                <th><fmt:message key="catalog.book"/></th>
                <th><fmt:message key="catalog.author"/></th>
                <th><fmt:message key="myBooks.button"/></th>
                <th>renew</th>
            </tr>
            </thead>
            <tbody id="bookTable">
            <c:forEach var="subscription" items="${subscriptions}">
                    <tr>
                        <c:choose>
                        <c:when test="${subscription.to lt today}">
                            <td style="color: red;">${subscription.books.id}</td>
                            <td style="color: red;">${subscription.books.name}</td>
                            <td style="color: red;">${subscription.books.author}</td>
                            <c:if test="${subscription.books.status eq 'SUBSCRIPTION'}">
                                <form method="post" action="myBooks">
                                    <input type="hidden" name="id" value=${subscription.books.id} />
                                    <input type="hidden" name="action" value="renew" />
                                    <td >
                                        <input type="submit" class="btn btn-outline-primary" value="<fmt:message key="myBooks.renew"/> ">
                                    </td>
                                </form>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <td>${subscription.books.id}</td>
                            <td>${subscription.books.name}</td>
                            <td>${subscription.books.author}</td>
                        </c:otherwise>
                        </c:choose>
                        <form method="post" action="myBooks">
                            <input type="hidden" name="id" value=${subscription.books.id} />
                            <input type="hidden" name="status" value=${subscription.books.status} />
                            <td id="action">
                                <input type="submit" class="btn btn-outline-danger" value="<fmt:message key="myBooks.button"/>">
                            </td>
                        </form>
                    </tr>
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
