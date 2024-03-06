<%-- 
    Document   : orderStatus
    Created on : Mar 5, 2024, 7:45:38 PM
    Author     : duclu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Status Page</title>
    </head>
    <body>
        <h1>ORDER SUCCESSFULLY!</h1>
        <h3>Your orderID is ${requestScope.SUCCESS_ORDER.orderID}</h3>
        <a href="shopping.html">Go back</a>
    </body>
    <script>
        setTimeout(() => {
            window.location.href ="shopping.html" ;
        }, 10000);
        
    </script>
</html>
