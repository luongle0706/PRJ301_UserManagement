<%-- 
    Document   : admin
    Created on : Feb 19, 2024, 4:36:58 PM
    Author     : duclu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="sample.sp24.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
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
            h1 {
                color: white;
            }
            .center {
                display: flex;
                justify-content: center;
            }
            .search {
                max-width: 400px;
                margin: 20px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            input[type="text"], input[type="password"] {
                width: calc(100% - 20px);
                padding: 10px;
                margin: 5px 0;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            input[type="submit"], input[type="reset"], a.button {
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

            input[type="submit"]:hover, input[type="reset"]:hover, a.button:hover {
                background-color: #3385ff;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            table, th, td {
                border: 1px solid #ddd;
            }

            th, td {
                padding: 10px;
                text-align: left;
                color: black;
            }

            th {
                background-color: #0066ff;
                color: #fff;
            }

            tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            tr:hover {
                background-color: #e5e5e5;
            }

            .error {
                display: flex;
                justify-content: center;
                color: white;
                font-size: larger;
                margin-top: 10px;
            }

            h1{
                text-align: center;
            }

            .container-logout-create{
                display: flex;
                justify-content: center;
                gap: 10%;
            }
            .container-logout-create a {
                text-decoration: none;
                color: white;
            }
            .container-logout-create a:hover {
                color: black;
            }
            .button {
                text-decoration: none;
                background-color: #EEEEEE;
                color: #333333;
                padding: 2px 6px 2px 6px;
                border-top: 1px solid #CCCCCC;
                border-right: 1px solid #333333;
                border-bottom: 1px solid #333333;
                border-left: 1px solid #CCCCCC;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'AD'}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>
        <h1>Welcome: ${sessionScope.LOGIN_USER.fullName}</h1>
        <c:url var="logoutLink" value="MainController">
            <c:param name="action" value="Logout"></c:param>
        </c:url>
        <div class="center">
            <a class="button" href="${logoutLink}">Logout</a>
            <a class="button" href="MainController?action=Create_User_Page">Create User</a>
        </div>

        <form action="MainController" method="POST" class="search">
            Search:<input type="text" name="search" value="${param.search}"/>
            <input type="submit" name="action" value="Search"/>                   
        </form>
        <c:if test="${requestScope.LIST_USER != null}">
            <c:if test="${not empty requestScope.LIST_USER}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>User ID</th>
                            <th>Full Name</th>
                            <th>Role ID</th>
                            <th>Password</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                        <form action="MainController" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td><input type="text" name="userID" value="${user.userID}" readonly=""</td>
                                <td><input type="text" name="fullName" value="${user.fullName}" required=""</td>
                                <td><input type="text" name="roleID" value="${user.roleID}" required=""</td>
                                <td>${user.password}</td>
                                <!--update o day ne -->
                                <td>
                                    <input type="submit" name="action" value="Update"/>
                                    <input type="hidden" name="search" value="${param.search}"/>
                                </td> 
                                <!--delete o day ne -->
                                <td>
                                    <c:url var="deleteLink" value="MainController">
                                        <c:param name="action" value="Delete"></c:param>
                                        <c:param name="userID" value="${user.userID}"></c:param>
                                        <c:param name="search" value="${param.search}"></c:param>
                                    </c:url>
                                    <a class="button"href="${deleteLink}">Delete</a>
                                </td>
                            </tr>
                        </form>  
                    </c:forEach>

                </tbody>
            </table>
            <div class="error">
                ${requestScope.ERROR}
            </div>

        </c:if>

    </c:if>
</body>
</html>
