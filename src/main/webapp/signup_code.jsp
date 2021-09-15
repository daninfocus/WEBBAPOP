<%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 13/09/2021
  Time: 16:05
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
            <h2 class="title">Verifica tu correo</h2>
            <form action="/CreateUser" method="POST" class="the-form">
                <label for="code">Code</label>
                <input type="hidden" name="name" value="<%=request.getParameter("name")%>">
                <input type="hidden" name="email" value="<%=request.getParameter("email")%>">
                <input type="hidden" name="password" value="<%=request.getParameter("password")%>">
                <input type="hidden" name="password2" value="<%=request.getParameter("password2")%>">
                <input type="number" name="code" id="code" placeholder="Inserta tu codigo" required>
                <input type="submit" value="Crear una cuenta">
            </form>
        </div><!-- FORM BODY-->
    </div><!-- FORM CONTAINER -->
</div>

</body>
</html>
