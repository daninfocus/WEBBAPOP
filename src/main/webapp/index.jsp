<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link
            rel="shortcut icon"
            type="img/favicon.ico"
            href="resources/img/logo512circle.png"
    />
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="resources/FontAwesome/css/all.css" rel="stylesheet" />
    <!--load all styles -->
    <link rel="stylesheet" href="resources/css/style.css" />
    <link rel="manifest" href="manifest.json" />
    <meta name="theme-color" content="#30ab53" />
    <link rel="apple-touch-icon" href="resources/img/logo192.png" />
    <title>Webbapop</title>
</head>
<body>
<div class="header">
    <!-----------------------------------------------------------------------------------------------------------------SearchBAR -->
    <a href="${pageContext.request.contextPath}/" class="logo"><i class="fab fa-weebly"></i>ebbaPop</a
    ><!--Webbapop logo -->

    <div class="wrap">
        <!-----------------------------------------------------------SearchBAR -->
        <div class="search">
            <input type="text" class="searchTerm" placeholder=" Search" />
            <button type="submit" class="searchButton">
                <i class="fa fa-search"></i>
            </button>
        </div>
    </div>

    <div class="header-right">
        <!-----------------------------------------Header right options -->
        <a class="option1" href="/Login">Sign in</a>
        <a class="option2" href="/Signup">Sign up</a>

        <!--<a href="hello-servlet">Hello Servlet</a>-->
    </div>
</div>
<!-------------------------------------------------------------------------------------------------------------------------------SearchBAR -->

<div class="home-title">¿Qué buscas hoy?</div>

<!--<div class="content"></div>-->
<div class="footer">
    <p>
        <a href="https://github.com/daninfocus" target="_blank"
        ><i class="fab fa-github"></i
        ></a>
        <a href="https://twitter.com/daninfocus" target="_blank"
        ><i class="fab fa-twitter-square"></i
        ></a>
        <a href="https://www.linkedin.com/in/danielwebb99" target="_blank"
        ><i class="fab fa-linkedin"></i
        ></a>
    </p>
</div>

<script src="resources/js/index.js"></script>
</body>
</html>



