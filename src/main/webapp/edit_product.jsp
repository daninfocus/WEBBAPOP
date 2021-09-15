<%@ page import="Modelo.Producto" %>
<%@ page import="Modelo.Usuario" %>
<%@ page import="Modelo.GestionAPP" %><%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 12/09/2021
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    GestionAPP gestion = (GestionAPP) session.getAttribute("gestion");
    Usuario user = gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString());
    String option = request.getParameter("editProduct");

    boolean hasThisProduct = false;
    Producto producto = null;

    if (option != null) {
        for (Producto productos : gestion.getProductosDeUsuario(user.getId())) {
            if (productos.getid() == Integer.parseInt(option)) {
                hasThisProduct = true;
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link href="./resources/FontAwesome/css/all.css" rel="stylesheet"/>
    <!--load all styles -->
    <link rel="stylesheet" href="./resources/css/edit_product.css"/>
    <script language="JavaScript" src="<%=request.getContextPath()%>./resources/js/user.js"></script>
</head>
<body>
<div class="boxDad">
    <h2 style="color:white">Editar producto</h2>
    <h4 style="color: rgb(156, 156, 156);font-weight: normal">Aquí puedes editar un producto</h4>
    <div class="flexContainer">
        <a href="#"  class="flexChild" id="motor">
            <i class="fas fa-car-side"></i>
            Motor
        </a>
        <a href="#"  class="flexChild" id="properties">
            <i class="fas fa-home"></i>
            Propiedad
        </a>
        <a href="#"  class="flexChild" id="technology">
            <i class="fas fa-tv"></i>
            Informática
        </a>
        <a href="#" class="flexChild" id="fashion">
            <i class="fas fa-tshirt"></i>
            Moda
        </a>
        <a href="#" class="flexChild" id="other" >
            <i class="fas fa-ellipsis-h"></i>
            Otro
        </a>
    </div>
    <%
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        if (hasThisProduct) {
            producto = gestion.getProductoPorID(Integer.parseInt(option));
            if (producto.getCategoria().equals("Motor")) {

                out.print("<div class=\"page-visible\" id=\"motor-form\">\n" +
                        "        <form action=\"/Save?Option=Edit&Product_ID="+producto.getid()+"\" method=\"post\" class=\"form\" >\n" +
                        "            <div class=\"form-contents\">\n" +
                        "                <div class=\"make\">\n" +
                        "                    <div><label for=\"name\">Marca</label></div>\n" +
                        "                    <input type=\"text\" id=\"name\" name=\"name\" placeholder=\"  Ej: Volkswagen\" required value="+producto.getNombre()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"model\">\n" +
                        "                    <div><label for=\"model\">Modelo&emsp;</label></div>\n" +
                        "                    <input type=\"text\" id=\"model\" name=\"model\" placeholder=\"  Ej: Golf 4\" required value="+producto.getExtraInfo()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"price\">\n" +
                        "                    <div><label for=\"price\">Precio&emsp;</label></div>\n" +
                        "                    <input style=\"width: 100%\" name=\"price\" placeholder=\"  Con cabeza...\" required value="+producto.getPrecio()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"state\">\n" +
                        "                    <div><label for=\"model\">Estado&emsp;</label></div>\n" +
                        "                    <input type=\"text\" id=\"state\" name=\"state\" placeholder=\"  Honestidad...\" required value="+producto.getEstado()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"description\">\n" +
                        "                    <div><label for=\"description\">Descripcion&emsp;</label></div>\n" +
                        "                    <textarea name=\"description\" placeholder=\"  Una breve descripcion de su vehiculo\" required >"+producto.getDescripcion()+"</textarea>\n" +
                        "                </div>\n" +
                        "                        Selecciona una foto:<input type=\"file\" name=\"fileName\">\n" +
                        "                <input type=\"hidden\" name=\"category\" value=\"Motor\" />\n" +
                        "                <input type=\"hidden\" name=\"userId\" value="+user.getId()+" />\n" +
                        "    <input type=\"hidden\" name=\"sold\" value=\"no\" />\n" +
                        "</div>\n" +
                        "<div class=\"save\"><button type=\"submit\">Guardar</button></div>\n" +
                        "</form>\n" +
                        "</div>"+
                        "           <style>\n" +
                        "                #motor{\n" +
                        "                    color: white;\n" +
                        "                    background-color: #30ab53;\n" +
                        "                    border-radius: 5px;\n" +
                        "                }\n" +
                        "            </style>");

            }
            if (producto.getCategoria().equals("Property")) {

                out.print("<div class=\"page-visible\" id=\"prop-form\">\n" +
                        "        <form action=\"/Save?Option=Edit&Product_ID="+producto.getid()+"\" method=\"POST\" class=\"form\" >\n" +
                        "            <div class=\"form-contents\">\n" +
                        "                <div class=\"tittle\">\n" +
                        "                    <div><label for=\"name\">Título</label></div>\n" +
                        "                    <input type=\"text\" name=\"name\" placeholder=\"  Titulo del anuncio\" required value="+producto.getNombre()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"type\">\n" +
                        "                    <div><label for=\"type\">Tipo</label></div>\n" +
                        "                    <input type=\"text\" name=\"extraInfo\" placeholder=\"  Ej: Cochera, Cortijo\" required value="+producto.getExtraInfo()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"price\">\n" +
                        "                    <div><label for=\"price\">Precio&emsp;</label></div>\n" +
                        "                    <input style=\"width: 100%\" name=\"price\" placeholder=\"  Con cabeza...\" required value="+producto.getPrecio()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"state\">\n" +
                        "                    <div><label for=\"model\">Estado&emsp;</label></div>\n" +
                        "                    <input type=\"text\" name=\"state\" placeholder=\"  Ej: Obra nueva, Reformar...\" required value="+producto.getEstado()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"description\">\n" +
                        "                    <div><label for=\"description\">Descripcion&emsp;</label></div>\n" +
                        "                    <textarea name=\"description\" placeholder=\"  Una breve descripcion de su propiedad\" required>"+producto.getDescripcion()+"</textarea>\n" +
                        "                </div>\n" +
                        "                        Selecciona una foto:<input type=\"file\" name=\"fileName\">\n" +
                        "                <input type=\"hidden\" name=\"category\" value=\"Property\" />\n" +
                        "                <input type=\"hidden\" name=\"userId\" value="+user.getId()+" />\n" +
                        "    <input type=\"hidden\" name=\"sold\" value=\"no\" />\n" +
                        "</div>\n" +
                        "<div class=\"save\"><button type=\"submit\">Guardar</button></div>\n" +
                        "</form>\n" +
                        "</div>"+
                        "           <style>\n" +
                        "                #properties{\n" +
                        "                    color: white;\n" +
                        "                    background-color: #30ab53;\n" +
                        "                    border-radius: 5px;\n" +
                        "                }\n" +
                        "            </style>");
            }
            if (producto.getCategoria().equals("Informática")) {


                out.print("<div class=\"page-visible\" id=\"technology-form\">\n" +
                        "        <form action=\"/Save?Option=Edit&Product_ID="+producto.getid()+"\" method=\"POST\" class=\"form\" >\n" +
                        "            <div class=\"form-contents\">\n" +
                        "                <div class=\"tittle\">\n" +
                        "                    <div><label for=\"name\">Título</label></div>\n" +
                        "                    <input type=\"text\" name=\"name\" placeholder=\"  Titulo del anuncio\"  required value="+producto.getNombre()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"type\">\n" +
                        "                    <div><label for=\"type\">Tipo</label></div>\n" +
                        "                    <input type=\"text\"  name=\"extraInfo\" placeholder=\"  Ej: Portatil, Telefono\"  required value="+producto.getExtraInfo()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"price\">\n" +
                        "                    <div><label for=\"price\">Precio&emsp;</label></div>\n" +
                        "                    <input style=\"width: 100%\" name=\"price\" placeholder=\"  Con cabeza...\" required value="+producto.getPrecio()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"state\">\n" +
                        "                    <div><label for=\"model\">Estado&emsp;</label></div>\n" +
                        "                    <input type=\"text\"  name=\"state\" placeholder=\"  Ej: Como nuevo..\" required value="+producto.getEstado()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"description\">\n" +
                        "                    <div><label for=\"description\">Descripcion&emsp;</label></div>\n" +
                        "                    <textarea name=\"description\" placeholder=\"  Una breve descripcion de su producto\" required>"+producto.getDescripcion()+"</textarea>\n" +
                        "                </div>\n" +
                        "                        Selecciona una foto:<input type=\"file\" name=\"fileName\">\n" +
                        "                <input type=\"hidden\" name=\"category\" value=\"Informática\" />\n" +
                        "                <input type=\"hidden\" name=\"userId\" value="+user.getId()+" />\n" +
                        "    <input type=\"hidden\" name=\"sold\" value=\"no\" />\n" +
                        "</div>\n" +
                        "<div class=\"save\"><button type=\"submit\">Guardar</button></div>\n" +
                        "</form>\n" +
                        "</div>"+
                        "           <style>\n" +
                        "                #technology{\n" +
                        "                    color: white;\n" +
                        "                    background-color: #30ab53;\n" +
                        "                    border-radius: 5px;\n" +
                        "                }\n" +
                        "            </style>");

            }
            if (producto.getCategoria().equals("Moda")) {
                out.print("<div class=\"page-visible\" id=\"fashion-form\">\n" +
                        "        <form action=\"/Save?Option=Edit&Product_ID="+producto.getid()+"\" method=\"POST\" class=\"form\" >\n" +
                        "            <div class=\"form-contents\">\n" +
                        "                <div class=\"tittle\">\n" +
                        "                    <div><label for=\"name\">Título</label></div>\n" +
                        "                    <input type=\"text\" name=\"name\" placeholder=\"  Titulo del anuncio\" required value="+producto.getNombre()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"type\">\n" +
                        "                    <div><label for=\"type\">Tipo</label></div>\n" +
                        "                    <input type=\"text\"  name=\"extraInfo\" placeholder=\"  Ej: Camiseta, Pantalones\" required value="+producto.getExtraInfo()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"price\">\n" +
                        "                    <div><label for=\"price\">Precio&emsp;</label></div>\n" +
                        "                    <input style=\"width: 100%\" name=\"price\" placeholder=\"  Con cabeza...\" required value="+producto.getPrecio()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"state\">\n" +
                        "                    <div><label for=\"model\">Estado&emsp;</label></div>\n" +
                        "                    <input type=\"text\"  name=\"state\" placeholder=\"  Ej: Como nuevo..\" required value="+producto.getEstado()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"description\">\n" +
                        "                    <div><label for=\"description\">Descripcion&emsp;</label></div>\n" +
                        "                    <textarea  id=\"description\" name=\"description\" placeholder=\"  Una breve descripcion de su propiedad\" required>"+producto.getDescripcion()+" </textarea>\n" +
                        "                </div>\n" +
                        "                        Selecciona una foto:<input type=\"file\" name=\"fileName\">\n" +
                        "                <input type=\"hidden\" name=\"category\" value=\"Moda\" />\n" +
                        "                <input type=\"hidden\" name=\"userId\" value=\"+user.getId()+\" />\n" +
                        "    <input type=\"hidden\" name=\"sold\" value=\"no\" />\n" +
                        "</div>\n" +
                        "<div class=\"save\"><button type=\"submit\">Guardar</button></div>\n" +
                        "</form>\n" +
                        "</div>"+
                        "           <style>\n" +
                        "                #fashion{\n" +
                        "                    color: white;\n" +
                        "                    background-color: #30ab53;\n" +
                        "                    border-radius: 5px;\n" +
                        "                }\n" +
                        "            </style>");
            }
            if (producto.getCategoria().equals("Other")) {

                out.print("<div class=\"page-visible\" id=\"other-form\">\n" +
                        "        <form action=\"/Save?Option=Edit&Product_ID="+producto.getid()+"\" method=\"POST\" class=\"form\" >\n" +
                        "            <div class=\"form-contents\">\n" +
                        "                <div class=\"title\">\n" +
                        "                    <div><label for=\"name\">Título</label></div>\n" +
                        "                    <input type=\"text\" name=\"name\" placeholder=\"  Titulo del anuncio\" required value="+producto.getNombre()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"type\">\n" +
                        "                    <div><label for=\"type\">Tipo</label></div>\n" +
                        "                    <input type=\"text\" id=\"type\" name=\"extraInfo\" placeholder=\"  Ej: Servicios, Hogar...\" required value="+producto.getExtraInfo()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"price\">\n" +
                        "                    <div><label for=\"model\">Precio&emsp;</label></div>\n" +
                        "                    <input style=\"width: 100%\" id=\"price\" name=\"price\" placeholder=\"  Con cabeza...\" required value="+producto.getPrecio()+">\n" +
                        "                </div>\n" +
                        "                <div class=\"description\">\n" +
                        "                    <div><label for=\"description\">Descripcion&emsp;</label></div>\n" +
                        "                    <textarea name=\"description\" placeholder=\"  Una breve descripcion de su propiedad\" >"+producto.getDescripcion()+"</textarea>\n" +
                        "                </div>\n" +
                        "                        Selecciona una foto:<input type=\"file\" name=\"fileName\">\n" +
                        "                <input type=\"hidden\" name=\"category\" value=\"Other\" />\n" +
                        "                <input type=\"hidden\" name=\"userId\" value=\"+user.getId()+\" />\n" +
                        "    <input type=\"hidden\" name=\"sold\" value=\"no\" />\n" +
                        "</div>\n" +
                        "<div class=\"save\"><button type=\"submit\">Guardar</button></div>\n" +
                        "</form>\n" +
                        "</div>"+
                        "           <style>\n" +
                        "                #other{\n" +
                        "                    color: white;\n" +
                        "                    background-color: #30ab53;\n" +
                        "                    border-radius: 5px;\n" +
                        "                }\n" +
                        "            </style>");

            }
        }
    %>


</div>
<script language="javascript">
    function clickMotor() {
        document.getElementById("motor-form").removeAttribute("class");
        document.getElementById("prop-form").removeAttribute("class");
        document.getElementById("fashion-form").removeAttribute("class");
        document.getElementById("other-form").removeAttribute("class");

        document.getElementById("motor-form").classList.add("page-visible");
        document.getElementById("prop-form").classList.add("page-invisible");
        document.getElementById("fashion-form").classList.add("page-invisible");
        document.getElementById("other-form").classList.add("page-invisible");
    }

    function clickFashion() {
        document.getElementById("motor-form").removeAttribute("class");
        document.getElementById("prop-form").removeAttribute("class");
        document.getElementById("fashion-form").removeAttribute("class");
        document.getElementById("other-form").removeAttribute("class");

        document.getElementById("motor-form").classList.add("page-invisible");
        document.getElementById("prop-form").classList.add("page-invisible");
        document.getElementById("fashion-form").classList.add("page-visible");
        document.getElementById("other-form").classList.add("page-invisible");
    }

    function clickProp() {
        document.getElementById("motor-form").removeAttribute("class");
        document.getElementById("prop-form").removeAttribute("class");
        document.getElementById("fashion-form").removeAttribute("class");
        document.getElementById("other-form").removeAttribute("class");

        document.getElementById("motor-form").classList.add("page-invisible");
        document.getElementById("prop-form").classList.add("page-visible");
        document.getElementById("fashion-form").classList.add("page-invisible");
        document.getElementById("other-form").classList.add("page-invisible");
    }

    function clickOther() {
        document.getElementById("motor-form").removeAttribute("class");
        document.getElementById("prop-form").removeAttribute("class");
        document.getElementById("fashion-form").removeAttribute("class");
        document.getElementById("other-form").removeAttribute("class");

        document.getElementById("motor-form").classList.add("page-invisible");
        document.getElementById("prop-form").classList.add("page-invisible");
        document.getElementById("fashion-form").classList.add("page-invisible");
        document.getElementById("other-form").classList.add("page-visible");
    }
    function clickTechnology(){
        document.getElementById("motor-form").removeAttribute("class");
        document.getElementById("prop-form").removeAttribute("class");
        document.getElementById("fashion-form").removeAttribute("class");
        document.getElementById("other-form").removeAttribute("class");

        document.getElementById("motor-form").classList.add("page-invisible");
        document.getElementById("prop-form").classList.add("page-invisible");
        document.getElementById("fashion-form").classList.add("page-visible");
        document.getElementById("other-form").classList.add("page-invisible");
    }
</script>

</body>
</html>
