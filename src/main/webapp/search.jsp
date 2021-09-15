<%@ page import="Modelo.Producto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %><%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 15/09/2021
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="resources/css/home.css" />
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
    <div class="header-right">
        <!-----------------------------------------Header right options -->
        <%
            if (request.getSession().getAttribute("loggedInUser") == null) {
                out.print("        <a class=\"option1\" href=\"/Login\">Sign in</a>\n" +
                        "        <a class=\"option2\" href=\"/Signup\">Sign up</a>");
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

<div class="content">

    <%
        DecimalFormat df = new DecimalFormat("#,##0.##");
        if(request.getAttribute("productos")==null){
            out.print("Nada");
        }else{
            ArrayList<Producto> productos = (ArrayList<Producto>) request.getAttribute("productos");
            for (Producto producto : productos) {
                if (producto.getVendido() == 0 && producto.getDeleted()==0) {

                    out.print("<a target=\"_blank\" href=\"/Product?Product_ID=" + producto.getid() + "\">\n" +
                            "<div class=\"child-container\">\n" +
                            "    <div class=\"images\">\n" +
                            "        <img class=\"img\" src=\"image.jsp?imgID="+producto.getid()+"\"/>\n" +
                            "    </div>\n" +
                            "    <div class=\"product\">\n" +
                            "        <h2 class=\"price\">" + df.format(producto.getPrecio()) + " <i class=\"fa fa-euro-sign\" aria-hidden=\"true\"></i></h2 >\n" +
                            "        <h1 class=\"h1\">" + producto.getNombre() + "</h1>\n" +
                            "        <hr>" +
                            "        <p class=\"p\">" + producto.getDescripcion() + "</p>\n" +
                            "        <div class=\"buttons\">\n" +
                            "         \n" +
                            "        </div>" +
                            "    </div>\n" +
                            "</div></a>\n" +
                            "<br>");
                }
            }
        }
    %>
</div>
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