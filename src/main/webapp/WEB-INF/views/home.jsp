<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Web</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.js"></script>
    <script src="https://js.braintreegateway.com/js/braintree-2.32.1.min.js"></script>
    <link href="<c:url value='/'/>static/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container">
    <div class="col-md-4 col-md-offset-4">
        <form id="checkout" method="post" action='<c:url value="/checkout"/>'>
            <label for="amount">Amount</label>
            <input type="text" id="amount" name="amount"/> <br>
            <div id="payment-form"></div>
            <input type="submit" class="btn btn-primary" value="Pay Now">
        </form>
    </div>
</div>

<script>
    $.ajax({
        url: '<c:url value="/getToken"/>',
        type: 'get',
        dataType: 'text',
        success: function (res) {
            braintree.setup(res, "dropin", {
                container: "payment-form"
            });
        },
        error: function (error) {
        }
    })
</script>
</body>
</html>