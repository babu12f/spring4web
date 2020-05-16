<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Web</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<c:url value='/'/>static/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="https://js.stripe.com/v3/"></script>
</head>
<body>
<form action='<c:url value='/checkout'/>' method='POST' id='checkout-form'>
    <input type='hidden' value='${amount/100}' name='amount' />
    <h1>Price:<span/>${amount/100}</h1>
    <script
            src='https://checkout.stripe.com/checkout.js'
            class='stripe-button'
            data-key=${stripePublicKey}
            data-amount="${amount}"
            data-name='StackAbuse Services'
            data-description='Product Checkout'
            data-image='http://www.stackabuse.com/assets/images/sa-java-dark.svg?v=b5a08453df'
            data-locale='auto'
            data-email="false"
            data-zip-code='false'>
    </script>
</form>
</body>
</html>