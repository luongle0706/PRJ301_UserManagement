<%-- 
    Document   : shopping
    Created on : Feb 26, 2024, 8:40:29 AM
    Author     : duclu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
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
                max-width: 400px;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            input[type="text"]
            {
                width: 95%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }
            input[type="submit"],
            input[type="reset"] {
                width: 30%;
                padding: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }
            input[type="submit"]:hover,
            input[type="reset"]:hover {
                background-color: #0056b3;
            }
            #buttons {
                display: flex;
                justify-content: center;
                align-items: center;
                gap: 10px;
                padding-top: 5px;
            }
            select {
                background-color: lightblue; 
                border: 1px solid #ddd; 
                padding: 5px;
            }
        </style>
    </head>
    <body> 
        <div class="container">
            <h1 style="text-align: center">Gun for life 🗣️🔥</h1>
            <form action="MainController" method="POST">
                Select your weapon: <select name="weapon">
                    <option value="w001-glock-200">Glock - 200$</option>
                    <option value="w002-Luu dan-100">Lựu đạn - 100$</option>
                    <option value="w003-Xe tang-1000">Xe tăng - 1000$</option>
                    <option value="w004-Dao phay-50">Dao phay - 50$</option>
                    <option value="w005-May bay-5000">Máy bay - 5000$</option>
                </select>
                <br>Quantity: <select name="quantity">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="10">10</option>
                </select>
                <div id="buttons">
                    <input type="submit" name="action" value="Add"/>
                    <input type="submit" name="action" value="View"/>
                </div>
            </form>
            ${requestScope.MESSAGE}
        </div>
    </body>
</html>
