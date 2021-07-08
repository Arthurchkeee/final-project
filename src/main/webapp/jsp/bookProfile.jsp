<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 06.07.2021
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="main.jsp"/>
<div class="container">
    <div class="card-group">
<div class="card">
    <div class="card-body mx-auto">
        <img class="d-block" src="${pageContext.request.contextPath}${image}" alt="Responsive image">
    </div>
</div>
        <div class="card">
            <div class="card-body">
                <h3 class="h3 card-text">${name}</h3>
                <h2 class="h2 card-text"><fmt:message key="catalog.author"/> </h2>
                <p class="card-text">${author}</p>
                <h2 class="h2 card-text"><fmt:message key="catalog.genre"/> </h2>
                <p class="card-text">${genre}</p>
                <h2 class="h2 card-text"><fmt:message key="more.description"/> </h2>
                <p class="card-text">
                    ${description}</p>
                <c:choose>
                    <c:when test="${status == 'FREE'}">
                        <form method="get" action="order">
                            <input type="hidden" name="id" value=${id} />
                                <input type="radio" class="form-check-input" id="room" name="status" value="ROOM" checked>
                                <label for="room" ><fmt:message key="more.room"/></label><br>
                                <input type="radio" class="form-check-input" id="subscribe" name="status" value="SUBSCRIBE">
                                <label for="subscribe"><fmt:message key="more.subscription"/></label><br>
                                <input type="submit" class="btn btn-outline-success" value="<fmt:message key="orders.order"/>">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p class="card-text text-danger">
                            <fmt:message key="more.notAvailable"/>
                        </p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

</body>
</html>
