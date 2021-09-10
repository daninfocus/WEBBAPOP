<%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 03/08/2021
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link
            rel="shortcut icon"
            type="img/favicon.ico"
            href="resources/img/logo512circle.png"
    />
    <title>Signin to Webbapop | Webbapop</title>
    <link href="https://fonts.googleapis.com/css2?family=Muli:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/login.css">
</head>
<body>

<div class="main-container">
    <div class="form-container">

        <div class="source"><a title="Contratame" href="https://www.github.com/daninfocus/CV" target="_blank">Webbapop</a></div>

        <div class="form-body">
            <h2 class="title">Log in with</h2>
            <div class="social-login">
                <ul>
                    <li class="google"><a href="#">Google</a></li>

                </ul>
            </div><!-- SOCIAL LOGIN -->

            <div class="_or">or</div>

            <form action="/Home" method="POST" class="the-form">

                <label for="email">Email</label>
                <input type="email" name="email" id="email" placeholder="Enter your username">

                <label for="password">Password</label>
                <input type="password" name="password" id="password" placeholder="Enter your password">
                <%
                    String login_msg=(String)request.getAttribute("error");
                    if (login_msg!=null){
                        out.print("<font color=red size=3px>"+login_msg+"</font>");
                    }else{
                        out.print("<br>");
                    }

                %>
                <input type="submit" value="Log In">

            </form>


        </div><!-- FORM BODY-->

        <div class="form-footer">
            <div>
                <span>¿Don't have an account?</span> <a href="/Signup">Sign Up</a>
            </div>
        </div><!-- FORM FOOTER -->

    </div><!-- FORM CONTAINER -->
</div>

</body>
</html>
