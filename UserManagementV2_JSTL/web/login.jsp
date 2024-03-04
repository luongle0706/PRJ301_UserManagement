<%-- 
    Document   : login
    Created on : Feb 19, 2024, 4:34:08 PM
    Author     : duclu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
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
            .signIn {
                padding-top: 10px;
            }
            .signIn a {
                text-decoration: none;
                color: blue;
            }
            .signIn a:hover {
                color: #23d5ab;
            }
            #googleLoginBtn {
                margin-top: 10px;
                width: 50%;
                padding: 10px;
                background-color: red;
                color: #fff;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }
            #googleLoginBtn:hover {
                background-color: darkred;
            }
            .center {
                display: flex;
                justify-content: center;
            }
            #googleLoginBtn a {
                text-decoration: none;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Login</h2>
            <form action="MainController" method="POST" id="form" onsubmit="return validateRecaptcha()">
                <label for="userId">User ID:</label>
                <input type="text" id="userId" name="userID" required>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
                <div class="g-recaptcha" data-sitekey="6LcMe3spAAAAAOlRnXmcwnwjBnEB1cY8lWZCVBtr"></div>
                <div id="error"></div>
                <div id="buttons">
                    <input type="submit" name="action" value="Login">
                    <input type="reset" value="Reset">
                </div>
            </form>
            
            <div class="center">
                <div class="center" id="googleLoginBtn">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=openid%20profile%20email&redirect_uri=http://localhost:8080/UserManagementV2/LoginGoogleController&response_type=code
                       &client_id=522909224833-i52mbvud5du7ik3ko0fu2ohngcenfgpv.apps.googleusercontent.com&approval_prompt=force">
                        Login with Google
                    </a>
                </div>
            </div>

            <br><span style="color: red; font-size: larger;">${requestScope.ERROR}</span>
            
            <div class="signIn">
                <label>Don't have an account?</label>
                <a href="MainController?action=Sign_In_Page">Sign in</a>
            </div>
            
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
