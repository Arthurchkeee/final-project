<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 05.07.2021
  Time: 15:13
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
    <a class="btn btn-outline-dark m-lg-3" href="${pageContext.request.contextPath}/createUser" >Add User</a>
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
        <tbody id="myTable">
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.role}</td>

                        <td>
                            <form method="post" action="editUser">
                                <input type="hidden" name="id" value=${user.id} />
                                <input type="hidden" name="name" value=${user.name} />
                                <input type="submit" class="btn btn-outline-warning m-lg-3" value="Edit">
                            </form>
                            <form method="get" action="deleteUser">
                                <input type="hidden" name="id" value=${user.id} />
                                <input type="submit" class="btn btn-outline-danger m-lg-3" value="Delete">
                            </form>
                        </td>

            </tr>

        </c:forEach>
        </tbody>
    </table>
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
