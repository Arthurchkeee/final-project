<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: krysh
  Date: 25.05.2021
  Time: 09:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Обязательные метатеги -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a href="" class="navbar-brand">Library</a>
        <button class="navbar-toggler" tybe="button" data-toggle="collapse" data-target="#navbarContent"
                aria-controls="navbarContent" aria-expanded="false">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarContent">
            <ul     class="navbar-nav mr-auto mb-2">


            </ul>
        </div>
    </div>
</nav>
<div class="container">

    <div class="input-group " >
    <form class="mx-auto"  method="post" action="LoginUser">
        <span class="input-group-text" id="basic-addon1">Login</span>
        <input type="text" name="login" class="form-control" placeholder="Login"  aria-label="Login" aria-describedby="basic-addon1" value=${name} >
        <br>
        <span class="input-group-text" id="basic-addon2">Password</span>
        <input type="text" name="password" class="form-control" placeholder="Password" aria-label="Password" aria-describedby="basic-addon2"  >
        <br>
        <input type="submit" class="btn btn-outline-success" value="Sign In">

    </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</div>
</body>
</html>
