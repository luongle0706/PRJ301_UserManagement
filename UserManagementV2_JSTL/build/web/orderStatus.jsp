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
        <style>
            body {
                font-family: Arial, sans-serif;
                background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
                background-size: 400% 400%;
                animation: gradient 15s ease infinite;
                height: 100vh;
            }

            @keyframes gradient {
                0% {
                    background-position: 0% 50%;
                }
                50% {
                    background-position: 100% 50%;
                }
                100% {
                    background-position: 0% 50%;
                }
            }
            .container {
                max-width: 30%;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .buttons {
                width: 70%;
                padding: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }
            .buttons:hover {
                background-color: #0056b3;
            }
            .center {
                display: flex;
                justify-content: center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center;">ORDER SUCCESSFULLY!</h1>
            <h3>Your order Id is ${requestScope.SUCCESS_ORDER.orderID}</h3>
            <div class="center">
                <a class="center buttons" style="text-decoration: none; color:white;" href="shopping.html">Go back to Shopping page</a>
            </div>
        </div>
    </body>
    <script>
        setTimeout(() => {
            window.location.href = "shopping.html";
        }, 5000);

    </script>
</html>
