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
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<div class="container">
    <div class="card-group">
<div class="card">
    <div class="card-body mx-auto">
        <img class="d-block" src="<c:url value="${image}"/>" alt="Responsive image">
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
<section class="content-item" id="comments">
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <form method="post" action="comment">
                    <h3 class="pull-left">New Comment</h3>
                        <div class="row">
                            <div class="form-group col-xs-12 col-sm-9 col-lg-10">
                                <input type="text" class="fa-text-width" id="message" name="comment" placeholder="Your message" required=""></input>
                            </div>
                        </div>
                    <input type="hidden" name="id" value=${id}>
                    <button type="submit" class="btn btn-normal pull-right">Submit</button>
                </form>

                <h3>Comments</h3>

                <!-- COMMENT 1 - START -->
            <c:forEach var="comment" items="${comments}">
                <div class="card">
                    <div class="card-body">
                        <h4 class="media-heading">${comment.name}</h4>
                        <ul class="list-unstyled list-inline media-detail pull-left">
                            <li><i class="fa fa-calendar"></i>${comment.date}</li>
                            <li><i class="fa fa-comment-o"></i>${comment.text}</li>
                        </ul>
                    </div>
                </div>
                <!-- COMMENT 1 - END -->
            </c:forEach>
            </div>
        </div>
    </div>
</section>
</body>
</html>
