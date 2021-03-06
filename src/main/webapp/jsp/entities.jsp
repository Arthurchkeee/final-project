<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 05.07.2021
  Time: 15:05
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
    <a class="btn btn-outline-dark m-lg-3" href="${pageContext.request.contextPath}/users"><fmt:message
            key="entities.users"/></a>
    <a class="btn btn-outline-dark m-lg-3" href="${pageContext.request.contextPath}/books"><fmt:message
            key="books.name"/></a>
</div>
</body>
</html>
