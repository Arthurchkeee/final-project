<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    ${role}

        <input class="form-control" id="myInput" type="text" placeholder="Search..">
        <br>
        <table class="table table-bordered table-striped" id="detail_table">
            <thead class="thread-light">
            <tr>
                <th>Id</th>
                <th>Book</th>
                <th>Author</th>
                <th>Genre</th>
                <th>Status</th>
                <th>Choose</th>
                <th>Order</th>
            </tr>
            </thead>
            <tbody id="bookTable">
            <c:forEach var="book" items="${books}">
                <tr class="parent" id="a${book.id}" title="Click to expand/collapse" style="cursor: pointer;">
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.genre}</td>
                    <td>${book.status}</td>
                    <c:choose>
                    <c:when test="${book.status == 'FREE'}">
                    <form method="get" action="order">
                        <input type="hidden" name="id" value=${book.id} />
                    <td id="action">
                        <input type="radio" class="form-check-input" id="room" name="status" value="ROOM" checked>
                        <label for="room" >ROOM</label><br>
                        <input type="radio" class="form-check-input" id="subscribe" name="status" value="SUBSCRIBE">
                        <label for="subscribe">SUBSCRIBE</label><br>
                    </td>
                        <td>
                        <input type="submit" class="btn btn-outline-success" value="ORDER">
                    </td>
                    </form>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <input type="radio" class="form-check-input" id="room1" name="status" value="ROOM " disabled>
                            <label for="room" >ROOM</label><br>
                            <input type="radio" class="form-check-input" id="subscribe1" name="status" value="SUBSCRIBE" disabled>
                            <label for="subscribe">SUBSCRIBE</label>
                        </td>
                        <td>
                            <input type="submit" class="btn btn-outline-success" value="ORDER" disabled>
                        </td>
                    </c:otherwise>
                    </c:choose>

                    <div class="collapse" id="${book.name}">
                        <div class="card card-body">
                            Some placeholder content for the collapse component. This panel is hidden by default but revealed when the user activates the relevant trigger.
                        </div>
                    </div>
                </tr>
                <tr class="child-a${book.id}" style="display: none;">
                    <td> Collapse</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <p>
            <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                Link with href
            </a>
            <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                Button with data-bs-target
            </button>
        </p>
        <div class="collapse" id="collapseExample">
            <div class="card card-body">
                Some placeholder content for the collapse component. This panel is hidden by default but revealed when the user activates the relevant trigger.
            </div>
        </div>
        </div>





</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('tr.parent')
            .css("cursor", "pointer")
            .attr("title", "Click to expand/collapse")
            .click(function () {
                $(this).siblings('.child-' + this.id).toggle();
            });
        $('tr[@class^=child-]').hide().children('td');
    });
</script>
<script>
    $(document).ready(function(){
        $("#myInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#bookTable ").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</body>
</html>
