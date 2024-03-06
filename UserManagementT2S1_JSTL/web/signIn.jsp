<%-- 
    Document   : signIn
    Created on : Feb 22, 2024, 4:35:19 PM
    Author     : duclu
--%>

<%@page import="sample.sp24.t2s1.user.UserError" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In Page</title>
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
            input[type="text"],
            input[type="password"] {
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
            #buttons, #error, .g-recaptcha {
                display: flex;
                justify-content: center;
                align-items: center;
                gap: 10px;
                padding-top: 5px;
            }
            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Sign In</h2>
            <form action="MainController" method="POST" id="form" onsubmit="return validateRecaptcha()">
                <label>User ID:</label>
                <input type="text" id="userID" name="userID" required/>
                <label class="error">${requestScope.USER_ERROR.userIDError}</label><br>
                <label>Full name:</label>
                <input type="text" id="fullName" name="fullName" required/>
                <label class="error">${requestScope.ERROR.fullNameError}</label><br>
                <label>Password:</label>
                <input type="password" id="password" name="password" required/><br><br>
                <label>Confirm password:</label>
                <input type="password" id="confirm" name="confirm" required/>
                <label class="error">${requestScope.ERROR.confirmError}</label><br>
                <div class="g-recaptcha" data-sitekey="6LcMe3spAAAAAOlRnXmcwnwjBnEB1cY8lWZCVBtr"></div>
                <div id="error"></div>
                <div id="buttons">
                <input type="submit" name="action" value="SignIn"/>
                <input type="reset" value="Reset"/>
                </div>
                <br><label class="error">${requestScope.ERROR.error}</label>
            </form>
            
            <script src="https://www.google.com/recaptcha/api.js" async defer></script>
            <script>
                function validateRecaptcha() {
                    const response = grecaptcha.getResponse();
                    if (response) {
                        return true;
                    } else {
                        document.getElementById("error").innerHTML = "Please do ReCAPTCHA verification!";
                        document.getElementById("error").style.color = 'red';
                        return false;
                    }
                }
            </script>
        </div>
    </body>
</html>
