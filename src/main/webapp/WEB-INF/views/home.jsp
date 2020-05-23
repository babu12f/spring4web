<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Web</title>
    <link href="<c:url value='/'/>static/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form onsubmit="return false;">
    <div id="securepay-ui-container"></div>
    <button onclick="mySecurePayUI.tokenise();">Submit</button>
    <button onclick="mySecurePayUI.reset();">Reset</button>
</form>
<script id="securepay-ui-js" src="https://payments-stest.npe.auspost.zone/v3/ui/client/securepay-ui.min.js"></script>
<script type="text/javascript">
    var mySecurePayUI = new securePayUI.init({
        containerId: 'securepay-ui-container',
        scriptId: 'securepay-ui-js',
        clientId: '0oaxb9i8P9vQdXTsn3l5',
        merchantCode: '5AR0055',
        card: { // card specific config and callbacks
            onTokeniseSuccess: function (tokenisedCard) {
                // card was successfully tokenised
                console.log(tokenisedCard);
            }
        },
        onLoadComplete: function () {
            // the SecurePay UI Component has successfully loaded and is ready to be interacted with
            console.log("load complete");
        }
    });
</script>
</body>
</html>