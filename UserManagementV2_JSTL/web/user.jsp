<%-- 
    Document   : user
    Created on : Feb 19, 2024, 4:37:14 PM
    Author     : duclu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.sp24.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <style>
            body {
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
            .buttons {
                display: flex;
                justify-content: center;
            }
            input[type="submit"], a.button {
                background-color: #0066ff;
                color: #fff;
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                text-decoration: none;
                display: inline-block;
                margin: 5px;
            }
            input[type="submit"]:hover, a.button:hover {
                background-color: #3385ff;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1 style="color: red; text-align: center;">User page</h1></br>
            UserID: ${sessionScope.LOGIN_USER.userID}</br>
            FullName: ${sessionScope.LOGIN_USER.fullName}</br>
            RoleID: ${sessionScope.LOGIN_USER.roleID}</br>
            Password: ${sessionScope.LOGIN_USER.password}
        </div>
        <form class="buttons" action="MainController">
            <input type="submit" name="action" value="Shopping_Page"/>
            <input type="submit" name="action" value="Logout"/>
        </form> 
    </body>
</html>
