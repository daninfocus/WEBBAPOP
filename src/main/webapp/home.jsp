<%@ page import="Modelo.Usuario" %>
<%@ page import="Modelo.GestionAPP" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Modelo.Message" %>
<%@ page import="Modelo.Trato" %><%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 03/08/2021
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userEmail = (String) session.getAttribute("loggedInUser");
    if (userEmail == null) {
        response.sendRedirect("/Login");
        return; //the return is important; forces redirect to go now
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        /* Paste this css to your style sheet file or under head tag */
        /* This only works with JavaScript,
        if it's not present, don't show loader */
        .no-js #loader {
            display: none;
        }

        .js #loader {
            display: block;
            position: absolute;
            left: 100px;
            top: 0;
        }

        .se-pre-con {
            position: fixed;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            z-index: 9999;
            background: url("./resources/img/spinner.gif") center no-repeat #202020;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.js"></script>
    <script>

        //paste this code under the head tag or in a separate js file.
        // Wait for window load
        $(window).load(function () {
            // Animate loader off screen
            $(".se-pre-con").fadeOut("slow");

        });
    </script>

    <link
            rel="shortcut icon"
            type="img/favicon.ico"
            href="resources/img/logo512circle.png"
    />
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="resources/FontAwesome/css/all.css" rel="stylesheet"/>
    <!--load all styles -->
    <link rel="stylesheet" href="resources/css/home.css"/>
    <link rel="manifest" href="manifest.json"/>
    <meta name="theme-color" content="#30ab53"/>
    <link rel="apple-touch-icon" href="resources/img/logo192.png"/>
    <title>Webbapop</title>

</head>
<body>

<div class="se-pre-con"></div>
<div class="header">
    <!-----------------------------------------------------------------------------------------------------------------SearchBAR -->
    <a href="${pageContext.request.contextPath}/Home" class="logo"><i class="fab fa-weebly"></i>ebbaPop</a
    ><!--Webbapop logo -->
    <form action="/Search" method="get">
        <div class="wrap">
            <!-----------------------------------------------------------SearchBAR -->
            <div class="search">

                <input type="text" class="searchTerm" name="search" placeholder=" Search"/>
                <button type="submit" class="searchButton">
                    <i class="fa fa-search"></i>
                </button>

            </div>
        </div>
    </form>
    <div class="se-pre-con"></div>
    <div class="header-right">
        <!-----------------------------------------Header right options -->
        <%
            if (request.getSession().getAttribute("loggedInUser") == null) {

            } else {
                out.print("<a class=\"option1\" href=\"Profile\"><i  class=\"far fa-user fa-lg\"></i>&nbsp;&nbsp;" + request.getSession().getAttribute("name") + "</a>");
                out.print("<a class=\"option2\" href=\"/Profile?newProduct=true\"><i class=\"fa fa-plus-square\" ></i><div class=\"addProduct\">&nbsp;&nbsp;Subir un producto</div></a>");

                session.setAttribute("user", request.getAttribute("name"));      //set attribute in session
                session.setAttribute("email", request.getAttribute("email"));
            }
        %>
        <!--<a href="hello-servlet">Hello Servlet</a>-->
    </div>
</div>
<!-------------------------------------------------------------------------------------------------------------------------------SearchBAR -->

<div class="home-title">¿Qué buscas hoy?</div>

<jsp:include page="all_products.jsp"/>

<!--<div class="content"></div>-->

<%
    GestionAPP gestion = (GestionAPP) session.getAttribute("gestion");
    Usuario loggedInUser = gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString());
    ArrayList<Message> messages = gestion.getUnreadMessages(loggedInUser.getId());
    if (!messages.isEmpty() || messages.size() >= 2) {
        out.print("<a href=\"/Profile\" title=\"Tienes un mensaje nuevo\">\n" +
                "    <div class=\"notification-box\">\n" +
                "        <span class=\"notification-count\"><i class=\"far fa-comment-dots\"></i>" + messages.size() + "</span>\n" +
                "\n" +
                "        <div class=\"notification-bell\">\n" +
                "            <span class=\"bell-top\"></span>\n" +
                "            <span class=\"bell-middle\"></span>\n" +
                "            <span class=\"bell-bottom\"></span>\n" +
                "            <span class=\"bell-rad\"></span>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</a>");

    }


    Trato tratos = gestion.getNotFinishedTrato(userEmail);
    if (tratos != null && tratos.getTipoTrato().equals("Compra")) {
        out.print("<a href=\"/ReviewPage?Product_ID=" + tratos.getIdProducto() + "&OpenChats=Yes\" title=\"Tienes una reseña nueva\">\n" +
                "    <div class=\"notification-box\">\n" +
                "        <span class=\"notification-count\"><i class=\"fas fa-star-half-alt\"></i></span>\n" +
                "\n" +
                "        <div class=\"notification-bell\">\n" +
                "            <span class=\"bell-top\"></span>\n" +
                "            <span class=\"bell-middle\"></span>\n" +
                "            <span class=\"bell-bottom\"></span>\n" +
                "            <span class=\"bell-rad\"></span>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</a>");

    }

%>


<div class="footer">
    <div>
        <a href="https://github.com/daninfocus" target="_blank"
        ><i class="fab fa-github"></i
        ></a>
        <a href="https://twitter.com/daninfocus" target="_blank"
        ><i class="fab fa-twitter-square"></i
        ></a>
        <a href="https://www.linkedin.com/in/danielwebb99" target="_blank"
        ><i class="fab fa-linkedin"></i
        ></a>
    </div>
    <div style="color: darkslategrey;font-size: 16px;padding: 3px;">
        Designed by Daniel Webb
    </div>
</div>

<script src="resources/js/index.js"></script>
</body>
</html>
