<%@ page import="Modelo.Usuario" %>
<%@ page import="Modelo.GestionAPP" %>
<%@ page import="Modelo.SavedProducts" %>
<%@ page import="Modelo.Producto" %><%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 12/09/2021
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="e >
  <head>
    <meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" type="img/favicon.ico"
href="./resources/img/logo512circle.png"/>
<link href="./resources/FontAwesome/css/all.css" rel="stylesheet" />
<!--load all styles -->
<link rel="stylesheet" href="./resources/css/saved_products.css" />
<title>Webbapop | Reviews</title>
</head>
<body>
<div class="parent">
    <h2>Tus productos guardados</h2>
    <h4>
        Aquí puedes ver todos tus productos favoritos.
    </h4>
    <div class="listProductsSaved">
        <%
            GestionAPP gestion = (GestionAPP) session.getAttribute("gestion");
            Usuario loggedInUser = gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString());
            for (SavedProducts savedProduct : gestion.getSavedProducts(loggedInUser.getId())) {
                Producto producto = gestion.getProductoPorID(savedProduct.getProduct_ID());
                    out.print("<div class=\"savedListProducts\">\n" +
                            "            <img class= \"imgSaved\" src=\"image.jsp?imgID=" + producto.getid() + "\"/>\n" +
                            "            <div class= \"text-container \">\n" +
                            "                <div class=\"productName\">"+producto.getNombre()+"</div>\n" +
                            "                <div class=\"description\">"+producto.getDescripcion()+"</div>\n" +
                            "            </div>\n" +
                            "            <div class=\"buts\">\n" +
                            "                <a href=\"/SaveProduct?Delete="+producto.getid()+"&User_ID="+loggedInUser.getId()+"\"  class=\"deleteProd\"><i class=\"fas fa-heart-broken\"></i> Eliminar</a><br>\n" +
                            "                <a href=\"/OpenChat?ID_User="+loggedInUser.getId()+"&Product_ID="+producto.getid()+"\"  class=\"chatProd\"><i class=\"far fa-comment-dots\"></i> Mensaje</a>\n" +
                            "            </div>\n" +
                            "  </div>");

            }
        %>
    </div>

</div>
</body>
</html>

