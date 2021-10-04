<%@ page import="java.util.ArrayList" %>
<%@ page import="Modelo.Producto" %>
<%@ page import="Modelo.GestionAPP" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
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
<hr>
<div class="cat">
    <div class="technology"><a title="Informática" class="catOption" href="/Search?search=Informática"><i
            class="fas fa-tv"></i>Informática</a></div>
    <div class="motor"><a title="Motor" class="catOption" href="/Search?search=Motor"><i class="fas fa-car-side"></i>Motor</a>
    </div>
    <div class="property"><a title="Propiedades" class="catOption" href="/Search?search=Property"><i
            class="fas fa-home"></i>Property</a></div>
    <div class="fashion"><a title="Moda" class="catOption" href="/Search?search=Moda"><i class="fas fa-tshirt"></i>Moda</a>
    </div>
    <div class="other"><a title="Other" class="catOption" href="/Search?search=Other"><i class="fas fa-ellipsis-h"></i>Other</a>
    </div>
</div>

<div class="content">
<%
    GestionAPP gestion = new GestionAPP();
    request.getSession().setAttribute("gestion", gestion);
    ArrayList<Producto> productos = gestion.getProductos();
    DecimalFormat df = new DecimalFormat("#,##0.##");

    if (productos.size() > 3) {
        out.print("<div class=\"flex-container\">");
        for (int i = 0; i < 3; i++) {
            if (productos.get(i).getVendido() == 0 && productos.get(i).getDeleted() == 0) {

                out.print("<a href=\"/Product?Product_ID=" + productos.get(i).getid() + "\">\n" +
                        "<div class=\"child-container\">\n" +
                        "  <div class=\"images\">\n" +
                        "    <img\n" +
                        "            class=\"img\"\n" +
                        "            src=\"image.jsp?imgID=" + productos.get(i).getid() + "\"\n" +
                        "    />\n" +
                        (productos.get(i).getReserved() == 1 ? "<div class=\"reserved\"> <i class=\"far fa-bookmark\"></i></div>" : "") +
                        "  </div>\n" +
                        "    <div class=\"product\">\n" +
                        "        <h2 class=\"price\">" + df.format(productos.get(i).getPrecio()) + " <i class=\"fa fa-euro-sign\" aria-hidden=\"true\"></i></h2 >\n" +
                        "        <h1 class=\"h1\">" + productos.get(i).getNombre() + "</h1>\n" +
                        "        <hr>" +
                        "        <p class=\"p\">" + productos.get(i).getDescripcion() + "</p>\n" +
                        "        <div class=\"buttons\">\n" +
                        "         \n" +
                        "        </div>" +
                        "    </div>\n" +
                        "</div></a>\n" +
                        "<br>");
            }
        }
        out.print(" </div>");
    }
%>
</div>
<!--<div class="content"></div>-->
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



