<%-- 
    Document   : checkout
    Created on : Feb 27, 2024, 8:27:06 AM
    Author     : duclu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.sp24.t2s1.weapon.Cart"%>
<%@page import="sample.sp24.t2s1.weapon.Weapon"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
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
                max-width: 80%;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            table {
                width: 80%;
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
            .buttons {
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
        <h1 style="text-align: center; color: white; padding-top: 30px;">Checkout Page</h1>
        <div class="container mt-5">
            <div class="row">
                <div class="col-sm-6">
                    <table border="1">
                        <c:if test="${sessionScope.CART != null}">
                            <c:if test="${not empty sessionScope.CART}">
                                <h3>Review your order</h3>

                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="totalPrice" value="0.0"/>
                                    <c:forEach var="weapon" varStatus="counter" items="${sessionScope.CART.cart}">
                                        <c:set var="totalPrice" value="${totalPrice + (weapon.value.price * weapon.value.quantity)}"/>
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${weapon.value.id}</td>
                                            <td>${weapon.value.name}</td>
                                            <td>${weapon.value.price}$</td>
                                            <td>${weapon.value.quantity}</td>
                                            <td>${weapon.value.price * weapon.value.quantity}$</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </c:if>
                        </c:if>
                    </table>
                    <br><h4>Total: ${totalPrice}$</h3>
                </div>
                <div class="col-sm-6">
                    <h3>Delivery address</h3>
                    <form action="MainController" method="POST">
                        <label>Full name:</label>
                        <br><input type="text" id="fullName" name="fullName" required/><br>
                        <label style="color:red">${requestScope.ORDER_ERROR.fullNameError}</label><br>
                        <label>Phone number:</label>
                        <br><input type="text" id="phoneNumber" name="phoneNumber" required/><br>
                        <label style="color:red">${requestScope.ORDER_ERROR.phoneNumberError}</label><br>
                        <label>Delivery address:</label>
                        <br><input type="text" id="address" name="address" required/><br>
                        <label>Email address:</label>
                        <br><input type="text" id="email" name="email" required/><br>
                        <label>Payment method:</label>
                        <select name="paymentMethod">
                            <option value="cod">COD</option>
                            <option value="visa">Visa</option>
                            <option value="internetBanking">Internet Banking</option>
                        </select>
                        <input type="hidden" id="total" name="total" value="${totalPrice}"/>
                        <div class="buttons">
                            <input type="submit" name="action" value="Order"/>
                            <input type="reset" value="Reset"/>
                        </div>
                    </form>
                </div>
            </div>  
        </div>
    </body>
</html>
