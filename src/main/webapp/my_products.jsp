<%@ page import="Modelo.GestionAPP" %>
<%@ page import="Modelo.Usuario" %>
<%@ page import="Modelo.Producto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %><%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 20/08/2021
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    GestionAPP gestionAPP = new GestionAPP();
    Usuario user = gestionAPP.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString());
    ArrayList<Producto> productos = gestionAPP.getProductosDeUsuario(user.getId());
    int totalProd= productos.size();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="./resources/css/my_products.css"/>
    <link href="./resources/FontAwesome/css/all.css" rel="stylesheet"/>
    <title>Webbapop | Products</title>
    <script type="text/javascript">
        var total = "<%=totalProd%>";

        function truncateText(maxLength) {

            for(var i = 0; i <=total; i++){
                var selector='.name'+i;

                var element = document.querySelector(selector),
                    truncated = element.innerText;

                if (truncated.length > maxLength) {
                    truncated = truncated.substr(0, maxLength) + "...";
                }
                document.querySelector('.name'+i).innerText = truncated;
            }
            return truncated;
        }

    </script>
</head>
<body id="productBody" onload="truncateText(12)">
<div class="text1">
    <h2>Tus productos</h2>
    <h4>
        Aquí podrás subir productos, gestionar los que ya tienes y destacarlos
        para venderlos antes
    </h4>
</div>

<form action="/deleteProduct" method="post">

<%
    int num=0;
    DecimalFormat df = new DecimalFormat("#,##0.##");
    for (Producto producto : productos) {
        out.print("<input class=\"checkbox\" type=\"checkbox\" /><div class=\"list\">\n" +
                "    <img src=\"\"/>\n" +
                "    <div class=\"text-container\">\n" +
                "        <div class=\"price\">"+df.format(producto.getPrecio())+"<i class=\"fa fa-euro-sign\" aria-hidden=\"true\"></i></div>\n" +
                "        <div style=\"overflow:hidden;white-space: nowrap;\" class=\"name"+num+"\">"+producto.getNombre()+"</div>\n" +
                "    </div>\n" +
                "    <div class=\"publishDate-container\">\n" +
                "        <div class=\"text\">Publicado</div>\n" +
                "        <div class=\"publishDate\">"+producto.getFecha()+"</div>\n" +
                "    </div>\n" +
                "    <div class=\"modifyDate-container\">\n" +
                "        <div class=\"text\">Modify</div>\n" +
                "        <div class=\"modifyDate\">15/08/2021</div>\n" +
                "    </div>\n" +
                "    <div class=\"buttons\">\n" +
                "        <button onclick=\"window.location.href='/Sold?Product_ID="+producto.getid()+"';\" type=\"button\" title=\"Marcar como vendido\" class=\"sell\">\n" +
                "            <i class=\"far fa-handshake\"></i>\n" +
                "        </button>\n" +
                "        <button onclick=\"window.location.href='/Reserved?Product_ID="+producto.getid()+"';\" type=\"button\" title=\"Marcar como reservado\" class=\"reserve\">\n" +
                "            <i class=\"far fa-bookmark\"></i>\n" +
                "        </button>\n" +
                "        <button onclick=\"window.location.href='/Edit?Product_ID="+producto.getid()+"';\" type=\"button\" title=\"Editar\"class=\"edit\"><i class=\"far fa-edit\"></i></button>\n" +
                "    </div>\n" +
                "</div>" +
                "<br>");
        num++;
    }


%>
    <div style="width:100%;display: flex;flex-direction: row;justify-content: space-around">
        <input class="checkboxSave" type="submit" value="Borrar producto">
    </div>
</form>
</body>
</html>
