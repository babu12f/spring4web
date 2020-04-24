<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Register User</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <form class="form-horizontal" action="<c:url value="/register"/>" method="post">
        <sec:csrfInput/>
        <fieldset>

            <!-- Form Name -->
            <legend>User Registration</legend>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="name">Name :</label>
                <div class="col-md-4">
                    <input id="name" name="name" type="text" placeholder="Name" class="form-control input-md">

                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="email">Email :</label>
                <div class="col-md-4">
                    <input id="email" name="email" type="text" placeholder="Email" class="form-control input-md">

                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="username">Username :</label>
                <div class="col-md-4">
                    <input id="username" name="username" type="text" placeholder="Username" class="form-control input-md">

                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="password">Password</label>
                <div class="col-md-4">
                    <input id="password" name="password" type="password" placeholder="Password" class="form-control input-md">

                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="register"></label>
                <div class="col-md-4">
                    <button id="register" name="register" class="btn btn-primary">Sign Up</button>
                </div>
            </div>

        </fieldset>
    </form>

</div>
</body>
</html>
