<%@ page import="Modelo.GestionAPP" %>
<%@ page import="Modelo.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Modelo.Trato" %>
<%@ page import="Modelo.Producto" %><%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 10/09/2021
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="e >
  <head>
    <meta charset=" UTF-8
" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link
        rel="shortcut ico
      type=" img
/favicon.ico"
href="img/logo512circle.png"
/>
<link href="./resources/FontAwesome/css/all.css" rel="stylesheet"/>
<!--load all styles -->
<link rel="stylesheet" href="./resources/css/my_reviews.css"/>
<title>Webbapop | Reviews</title>
</head>
<body>


<%
    GestionAPP gestion = (GestionAPP) session.getAttribute("gestion");
    Usuario user = gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString());

    ArrayList<Trato> tratos = gestion.getAllTrato(user.getEmail());

%>
<div class="parent">
    <div class="titlecontainer">
        <h2 class="title">Tus opiniones</h2>

        <div class="points">
            <div >Tu media:</div>
            <div id="media" class="averageReview"><%=gestion.notaMedia(user.getEmail())%>
            </div>
        </div>

        <div class="countReviews">
            <div><%=tratos.size()%>
            </div>
            <div>Opiniones</div>
        </div>
    </div>
    <h4 class="desc">Esto es lo que piensan de ti.</h4>
    <div class="listReview">
        <%
            for (Trato trato : tratos) {
                if(trato.getCompletado()==1) {
                    Usuario usuarioOtro = gestion.getUsuarioPorEmail(trato.getEmailUsuarioOtro());
                    Producto producto = gestion.getProductoPorID(trato.getIdProducto());
                    out.print("<div class=\"review\">\n" +
                            "    <img\n" +
                            "            class=\"imgReview\"\n" +
                            "            src=\"image.jsp?imgID=" + producto.getid() + "\"\n" +
                            "    />\n" +
                            "            <div class=\"text-container \">\n" +
                            "                <div class=\"typeTrade\">" + (trato.getTipoTrato().equals("Venta") ? "Vendiste:" : "Compraste:") + "</div>\n" +
                            "                <div class=\"productName\">" + producto.getNombre() + "</div>\n" +
                            "                <div id=\"stars\" class=\"stars\">" + trato.getPuntuacion() + "</div>\n" +
                            "                <div class=\"reviewMessage\">" + (trato.getComentario() == null ? "Esperando opinion..." : trato.getComentario()) + "</div>\n" +
                            "                <div class=\"footerReview\">Por&nbsp;<a href=\"#Profile\">" + usuarioOtro.getNombre() + "</a>&nbsp;el&nbsp;<div\n" +
                            "                        class=\"dateReview\">" + trato.getFecha() + "\n" +
                            "                </div>\n" +
                            "                </div>\n" +
                            "            </div>\n" +
                            "        </div>");
                }
            }
        %>
        <script>
            let stars = document.getElementById("stars");
            let media = document.getElementById("media");

            if (media.textContent == 0) {
                media.textContent = "✰✰✰✰✰";
            }
            if (media.textContent == 1) {
                media.textContent = "⭐✰✰✰✰";
            }
            if (media.textContent == 2) {
                media.textContent = "⭐⭐✰✰✰";
            }
            if (media.textContent == 3) {
                media.textContent = "⭐⭐⭐✰✰";
            }
            if (media.textContent == 4) {
                media.textContent = "⭐⭐⭐⭐✰";
            }
            if (media.textContent == 5) {
                media.textContent = "⭐⭐⭐⭐⭐";
            }


            if (stars.textContent == 0) {
                stars.textContent = "✰✰✰✰✰";
            }
            if (stars.textContent == 1) {
                stars.textContent = "⭐✰✰✰✰";
            }
            if (stars.textContent == 2) {
                stars.textContent = "⭐⭐✰✰✰";
            }
            if (stars.textContent == 3) {
                stars.textContent = "⭐⭐⭐✰✰";
            }
            if (stars.textContent == 4) {
                stars.textContent = "⭐⭐⭐⭐✰";
            }
            if (stars.textContent == 5) {
                stars.textContent = "⭐⭐⭐⭐⭐";
            }
        </script>
    </div>
</div>
</body>
</html>
