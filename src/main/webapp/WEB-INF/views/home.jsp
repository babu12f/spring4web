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
<%--        <form id="checkout" method="post" action='<c:url value="/checkout"/>'>--%>
<%--            <label for="amount">Amount</label>--%>
<%--            <input type="text" id="amount" name="amount"/> <br>--%>
<%--            <div id="payment-form"></div>--%>
<%--            <input type="submit" class="btn btn-primary" value="Pay Now">--%>
<%--        </form>--%>

<%--        <form id="checkout" action="<c:url value="/checkout"/>" method="post">--%>
<%--            <input data-braintree-name="number" value="4111111111111111">--%>
<%--            <input data-braintree-name="expiration_date" value="10/20">--%>
<%--            <input type="submit" id="submit" value="Pay">--%>
<%--        </form>--%>
    </div>

    <div class="demo-frame">
        <form action="<c:url value="/checkout"/>" method="post" id="cardForm">
            <label class="hosted-fields--label" for="card-number">Card Number</label>
            <div id="card-number" class="hosted-field"></div>

            <label class="hosted-fields--label" for="expiration-date">Expiration Date</label>
            <div id="expiration-date" class="hosted-field"></div>

            <label class="hosted-fields--label" for="cvv">CVV</label>
            <div id="cvv" class="hosted-field"></div>

            <label class="hosted-fields--label" for="cvv">Postal Code</label>
            <div id="postal-code" class="hosted-field"></div>

            <div class="button-container">
                <input type="submit" class="button button--small button--green" value="Purchase" id="submit"/>
            </div>
        </form>
    </div>


</div>

<script>
    $(function () {
        $.ajax({
            url: '<c:url value="/getToken"/>',
            type: 'get',
            dataType: 'text',
            success: function (res) {
                // braintree.setup(res, "dropin", {
                //     container: "payment-form",
                //     onPaymentMethodReceived: function (obj) {
                //         // Do some logic in here.
                //         // When you're ready to submit the form:
                //         console.log(obj);
                //     },
                //     onError: function (obj) {
                //         console.log(obj);
                //         if (obj.type == 'VALIDATION') {
                //             // Validation errors contain an array of error field objects:
                //             obj.details.invalidFields;
                //
                //         } else if (obj.type == 'SERVER') {
                //             // If the customer's browser can't connect to Braintree:
                //             obj.message; // "Connection error"
                //
                //             // If the credit card failed verification:
                //             obj.message; // "Credit card is invalid"
                //             obj.details; // Object with error-specific information
                //         }
                //     }
                // });
                //braintree.setup(res, 'custom', {id: 'checkout'});
                createHostedFields(res);
            },
            error: function (error) {
            }
        });

        // $("form").on("submit", function (e) {
        //     e.preventDefault();
        //     console.log($(this).serialize());
        // });


        var checkout;

        function createHostedFields(authorization) {
            braintree.setup(authorization, "custom", {
                id: "cardForm",
                hostedFields: {
                    number: {
                        selector: "#card-number",
                        placeholder: "4111 1111 1111 1111"
                    },
                    cvv: {
                        selector: "#cvv",
                        placeholder: "111"
                    },
                    expirationDate: {
                        selector: "#expiration-date",
                        placeholder: "MM/YY"
                    },
                    postalCode: {
                        selector: "#postal-code",
                        placeholder: "3232323"
                    },
                    styles: {
                        'input': {
                            'font-size': '16px',
                            'font-family': 'courier, monospace',
                            'font-weight': 'lighter',
                            'color': '#ccc'
                        },
                        ':focus': {
                            'color': 'black'
                        },
                        '.valid': {
                            'color': '#8bdda8'
                        }
                    }
                },
                onReady: function (integration) {
                    checkout = integration;
                    console.log("integration : "+ integration)
                },
                onPaymentMethodReceived: function (res) {
                    alert('Submit your nonce to your server!');
                    // checkout.teardown(function () {
                    //     checkout = null;
                    //     createHostedFields();
                    // });
                    console.log(res);
                }
            });
        }

    });
</script>
</body>
</html>