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
    GestionAPP gestion = (GestionAPP)session.getAttribute("gestion");
    Usuario user = gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString());
    ArrayList<Producto> productos = gestion.getProductosDeUsuario(user.getId());
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
<body onload="truncateText(12)">
<div class="parentProduct">
    <div class="text1">
        <h2>Tus productos</h2>
        <h4>
            Aquí podrás subir productos y gestionar los que ya tienes.
        </h4>
    </div>
    <div class="tabs">

        <input type="radio" name="tabs" id="tabone" checked="checked">
        <label for="tabone">En Venta</label>
        <div class="tab">
            <h1>Tus productos en venta</h1>
            <form action="/deleteProduct" method="post">

                <%
                    int num=0;
                    DecimalFormat df = new DecimalFormat("#,##0.##");
                    for (Producto producto : productos) {
                        out.print(
                                "<input type=\"checkbox\" class=\"checkbox\" name=\"checkbox"+num+"\" value="+producto.getid()+">\n" +
                                        "<a class=\"list\" target=\"_blank\" href=\"/Product?Product_ID=" + producto.getid() + "\">\n" +
                                        "    <img\n" +
                                        "            class=\"img\"\n" +
                                        "            src=\"image.jsp?imgID="+producto.getid()+"\"\n" +
                                        "    />\n" +
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
                                        "</a>" +
                                        "<br>");
                        num++;
                    }
                %>
                <div style="width:100%;display: flex;flex-direction: row;justify-content: space-around">
                    <input type="hidden" name="userEmail" value="<%=session.getAttribute("loggedInUser")%>">
                    <input class="checkboxSave" type="submit" value="Borrar producto" onclick='return confirm("Se borrara permanentemente los productos seleccionados")'>
                </div>
            </form>
        </div>

        <input type="radio" name="tabs" id="tabtwo">
        <label for="tabtwo">Vendidos</label>
        <div class="tab">
            <h1>Tus productos vendidos</h1>
            <form action="/deleteProduct" method="post">

                <%
                    num=0;
                    df = new DecimalFormat("#,##0.##");

                    ArrayList<Producto> productosVendidos = gestion.getProductosDeUsuarioVendidos(user.getId());
                    for (Producto producto : productosVendidos) {
                        out.print(
                                "<input type=\"checkbox\" class=\"checkbox\" name=\"checkbox"+num+"\" value="+producto.getid()+">\n" +
                                        "<a class=\"list\" target=\"_blank\" href=\"/Product?Product_ID=" + producto.getid() + "\">\n" +
                                        "    <img\n" +
                                        "            class=\"img\"\n" +
                                        "            src=\"image.jsp?imgID="+producto.getid()+"\"\n" +
                                        "    />\n" +
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
                                        "</a>" +
                                        "<br>");
                        num++;
                    }
                %>
                <div style="width:100%;display: flex;flex-direction: row;justify-content: space-around">
                    <input type="hidden" name="userEmail" value="<%=session.getAttribute("loggedInUser")%>">
                    <input class="checkboxSave" type="submit" value="Borrar producto" onclick='return confirm("Se borrara permanentemente los productos seleccionados")'>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
