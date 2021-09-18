<%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 17/08/2021
  Time: 16:13
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
    <title>Registro Webbapop | Webbapop</title>
    <link href="https://fonts.googleapis.com/css2?family=Muli:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/signup.css">
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

            <form action="/CreateUser" method="POST" class="the-form">
                <label for="name">Nombre</label>
                <input type="text" name="name" id="name" placeholder="Inserta tu nombre" required>

                <label for="surname">Apellidos</label>
                <input type="text" name="surname" id="surname" placeholder="Inserta tu(s) apellido(s)" required>

                <label for="email">Email</label>
                <input type="email" name="email" id="email" placeholder="Inserta tu email" required>

                <label for="password"> Contraseña</label>
                <input type="password" name="password" id="password" placeholder="8 characteres, 1 mayus, 1 minus y 1 num" required>

                <label for="password2">Repita tu contraseña</label>
                <input type="password" name="password2" id="password2" placeholder="Repita tu contraseña" required>
                <%
                    String login_msg=(String)request.getAttribute("error");
                    if (login_msg!=null){
                        out.print("<font color=red size=3px>"+login_msg+"</font>");
                    }else{
                        out.print("<br>");
                    }

                %>
                <input type="submit" value="Crear una cuenta">

            </form>


        </div><!-- FORM BODY-->

        <div class="form-footer">
            <div>
                <span>¿Ya tienes una cuenta?</span> <a href="/Login">Log in</a>
            </div>
        </div><!-- FORM FOOTER -->

    </div><!-- FORM CONTAINER -->
</div>

</body>
</html>
