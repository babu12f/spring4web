<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Web</title>
    <link href="<c:url value='/'/>static/css/style.css" rel="stylesheet" type="text/css"/>
    <style>
        body {
            padding-top: 50px;
            color: green;
        }
    </style>
</head>
<body>
    <h1>Running spring app form jsp</h1>

<h3>${name}</h3>
<h3><c:out value="${name}"/></h3>
</body>
</html>