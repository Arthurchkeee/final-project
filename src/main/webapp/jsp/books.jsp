<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 05.07.2021
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<c:import url="entities.jsp"/>
<div class="container">
    <br>
    <a class="btn btn-outline-dark m-lg-3" href="${pageContext.request.contextPath}/createBook" ><fmt:message key="books.addBook"/></a>
    <br>
    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <br>


    <table id="books" class="table table-bordered table-striped">
        <thead class="thread-light">
        <tr>
            <th>№</th>
            <th><fmt:message key="books.name"/></th>
            <th><fmt:message key="books.author"/></th>
            <th><fmt:message key="books.genre"/></th>
            <th><fmt:message key="books.status"/></th>
            <th><fmt:message key="users.actions"/></th>
        </tr>
        </thead>
        <tbody id="myTable">
        <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.genre}</td>
                    <td>${book.status}</td>

                        <c:if test="${book.status == 'FREE'}">
                            <td>
                                <form method="post" action="editBook">
                                    <input type="hidden" name="id" value=${book.id} />
                                    <input type="hidden" name="name" value=${book.name} />
                                    <input type="hidden" name="author" value=${book.author} />
                                    <input type="submit" class="btn btn-outline-warning m-lg-3" value="<fmt:message key="books.edit"/>">
                                </form>
                                <form method="get" action="deleteBook">
                                    <input type="hidden" name="id" value=${book.id} />
                                    <input type="submit" class="btn btn-outline-danger m-lg-3" value="<fmt:message key="books.delete"/>">
                                </form>
                            </td>

                        </c:if>

                </tr>

        </c:forEach>
        </tbody>
    </table>

    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <form method="get" action="books">
                <c:forEach var="count" begin="1" end="${counts}">


                <form method="get" action="catalog">
                    <li  class="page-item"><input type="submit" class="page-link" name="page" value="${count}"/></li>


                    </c:forEach>
                </form>
        </ul>
    </nav>



</div>
<script>
    $(document).ready(function(){
        $("#myInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</body>
</html>
