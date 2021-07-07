<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 05.07.2021
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<c:import url="entities.jsp"/>
<div class="container">
    <div class="input-group mb-3">
        <form method="get" action="editBook">
            <input type="hidden" name="id" value=${id} />
        <span class="input-group-text" id="basic-addon1">Name</span>
        <input type="text" name="book_name" class="form-control" placeholder="Name"  aria-label="Name" aria-describedby="basic-addon1">
        <span class="input-group-text" id="basic-addon2">Author</span>
        <input type="text" name="author_name" class="form-control" placeholder="Author" aria-label="Author" aria-describedby="basic-addon2" >
        <span class="input-group-text" id="basic-select">Genre</span>
            <span class="input-group-text" id="basic-addon3">Description</span>
            <input type="text" name="description" class="form-control" placeholder="Description" aria-label="Description" aria-describedby="basic-addon3" >
            <span class="input-group-text" id="basic-addon4">Image Address</span>
            <input type="text" name="image" class="form-control" placeholder="Image Address" aria-label="Image Address" aria-describedby="basic-addon4" >

            <select class="form-select" name="genre" aria-describedby="basic-select">
            <option value="DETECTIVE">DETECTIVE</option>
            <option value="HORROR">HORROR</option>
            <option value="COMEDY">COMEDY</option>
            <option value="DRAMA" selected>DRAMA</option>
            <option value="ADULT">ADULT</option>
        </select>
            <input type="submit" class="btn btn-outline-success" value="Edit">
        </form>
    </div>
</div>
</body>
</html>