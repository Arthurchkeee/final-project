<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 25.05.2021
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    ${role}
    <a href="info.jsp">Profile</a>
    <a href="page.jsp">Show page</a>
    <a href="LogoutUser">Log out</a>
    <a href="catalog">catalog</a>
    <c:if test="${role eq 'LIBRERIAN'}">
    <form method="post" action="librarian">
        <td id="action">
            <input type="text" value="access">
        </td>
    </c:if>
    ${role}
</div>
</body>
</html>
