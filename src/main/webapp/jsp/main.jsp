<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 25.05.2021
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${not empty sessionScope.locale? sessionScope.locale:'en_US'}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html>
<head>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    </head>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a href="" class="navbar-brand">Library</a>
        <button class="navbar-toggler" tybe="button" data-toggle="collapse" data-target="#navbarContent"
                aria-controls="navbarContent" aria-expanded="false">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarContent">
            <ul     class="navbar-nav mr-auto mb-2">
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/catalog" class="nav-link">
                        <fmt:message key="nav.catalog"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/myBooks" class="nav-link">
                        <fmt:message key="nav.MyBooks"/>
                    </a>
                </li>
                <c:if test="${role eq 'LIBRERIAN'}">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/librarian" class="nav-link">
                            <fmt:message key="nav.Orders"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${role eq 'ADMIN'}">
                    <a href="${pageContext.request.contextPath}/jsp/entities.jsp" class="nav-link"><fmt:message key="nav.Entities"/></a>
                </c:if>


            </ul>
            <form class="offset-md-5" method="get" action="changeLocale">
                <input type="hidden" name="lang" value="ru"/>
                <input type="submit" class="btn btn-outline-primary " value="ru"/>
            </form>

            <form class="offset-md-1" method="get" action="changeLocale">
                <input type="hidden" name="lang" value="en"/>
                <input type="submit" class="btn btn-outline-primary " value="en"/>
            </form>

            <form class="offset-md-1" method="get" action="changeLocale">
                <input type="hidden" name="lang" value="by"/>
                <input type="submit" class="btn btn-outline-primary" value="by"/>
            </form>
            <form class="offset-md-1">
                <input type="hidden" class="form-control ">
                <a class="btn btn-outline-danger " href="${pageContext.request.contextPath}/LogoutUser"><fmt:message key="nav.SignOut"/></a>
            </form>
        </div>
    </div>
</nav>




<div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</div>
</body>
</html>
