<%@ page import="Modelo.GestionAPP" %>
<%@ page import="Modelo.Producto" %>
<%@ page import="java.text.DecimalFormat" %><%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 10/08/2021
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
    <link rel="stylesheet" href="resources/css/all_product.css"/>
    <link href="resources/FontAwesome/css/all.css" rel="stylesheet"/>

</head>
<body>
<table>
    <%
        GestionAPP gestionAPP = new GestionAPP();
        int conta = 0;
        DecimalFormat df = new DecimalFormat("#,##0.##");

        out.print("<div class=\"flex-container\">");
        for (Producto producto : gestionAPP.getProductos()) {
            if(producto.getVendido()==0) {
                out.print("<a target=\"_blank\" href=\"/Product?Product_ID=" + producto.getid() + "\">\n" +
                        "<div class=\"child-container\">\n" +
                        "    <div class=\"images\">\n" +
                        "        <img class=\"img\" src=\"http://mistillas.cl/wp-content/uploads/2018/04/Nike-Epic-React-Flyknit-%E2%80%9CPearl-Pink%E2%80%9D-01.jpg\" />\n" +
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
        out.print(" </div>");

    %>

</table>

</body>
</html>
