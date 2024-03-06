<%-- 
    Document   : viewCart
    Created on : Feb 26, 2024, 8:47:33 AM
    Author     : duclu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.sp24.weapon.Weapon"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="sample.sp24.weapon.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
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
                max-width: 1000px;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            table {
                width: 50%;
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
            input[type="submit"] {
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
            input[type="submit"]:hover {
                background-color: #3385ff;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center;">Your selected</h1>

            <c:if test="${sessionScope.CART != null}">
                <c:if test="${not empty sessionScope.CART}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                                <th>Edit</th>
                                <th>Remove</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="totalPrice" value="0.0"/>
                            <c:forEach var="weapon" varStatus="counter" items="${sessionScope.CART.cart}">
                                <c:set var="totalPrice" value="${totalPrice + (weapon.value.price * weapon.value.quantity)}"/>
                            <form method="POST">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>
                                        <input type="text" name="id" value="${weapon.value.id}" readonly=""/>
                                    </td>
                                    <td>${weapon.value.name}</td>
                                    <td>${weapon.value.price}$</td>
                                    <td><input type="number" name="quantity" value="${weapon.value.quantity}" required="" min="1"/></td>
                                    <td>${weapon.value.price * weapon.value.quantity}$</td>
                                    <!--edit o day-->
                                    <td>
                                        <input type="submit" name="action" value="Edit"/>
                                    </td>
                                    <!--remove o day-->
                                    <td>
                                        <input type="submit" name="action" value="Remove"/>
                                    </td>
                                </tr>
                            </form>              
                        </c:forEach>
                        </tbody>
                    </table>
                    <h3>Total: ${totalPrice}$</h3>
                    ${requestScope.CHECKOUT_ERROR.quantityError}
                    <form action="MainController" method="POST">
                        <input type="submit" name="action" value="Checkout"/>
                    </form>
                </c:if>
            </c:if>
            ${requestScope.SESSION_ERROR}
        </div>
    </body>
</html>
