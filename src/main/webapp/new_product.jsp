<%@ page import="Modelo.GestionAPP" %>
<%@ page import="Modelo.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 22/08/2021
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    GestionAPP gestion = (GestionAPP) session.getAttribute("gestion");
    Usuario user = gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString());
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="./resources/css/new_product.css"/>
    <link href="./resources/FontAwesome/css/all.css" rel="stylesheet"/>
    <title>Webbapop | Products</title>
</head>
<body>
<div class="boxDad">
    <h2 style="color:white">Producto nuevo</h2>
    <h4 style="color: rgb(156, 156, 156);font-weight: normal">Aquí puedes registrar un producto nuevo</h4>
    <div class="flexContainer">
        <button class="flexChild" id="motor" onclick="clickMotor()">
            <i class="fas fa-car-side"></i>
            Motor
        </button>
        <button class="flexChild" id="properties" onclick="clickProp()">
            <i class="fas fa-home"></i>
            Propiedad
        </button>
        <button class="flexChild" id="technology" onclick="clickTechnology()">
            <i class="fas fa-tv"></i>
            Informática
        </button>
        <button class="flexChild" id="fashion" onclick="clickFashion()">
            <i class="fas fa-tshirt"></i>
            Moda
        </button>
        <button class="flexChild" id="other" onclick="clickOther()">
            <i class="fas fa-ellipsis-h"></i>
            Otro
        </button>
    </div>

    <div class="page-invisible" id="motor-form">
        <form action="/Save" method="post" class="form" enctype="multipart/form-data">
            <div class="form-contents">
                <div class="make">
                    <div><label for="name">Marca</label></div>
                    <input type="text" id="name" name="name" placeholder="  Ej: Volkswagen" required/>
                </div>
                <div class="model">
                    <div><label for="model">Modelo&emsp;</label></div>
                    <input type="text" id="model" name="model" placeholder="  Ej: Golf 4" required/>
                </div>
                <div class="price">
                    <div><label for="price">Precio&emsp;</label></div>
                    <input style="width: 100%" name="price" placeholder="  Con cabeza..." required/>
                </div>
                <div class="state">
                    <div><label for="model">Estado&emsp;</label></div>
                    <input type="text" id="state" name="state" placeholder="  Honestidad..." required/>
                </div>
                <!--<div class="km">
                    <div><label for="km">Kilómetros&emsp;</label></div>
                    <input type="text" id="km" name="km" placeholder="  Kilómetros actuales" required/>
                </div>-->
                <div class="description">
                    <div><label for="description">Descripcion&emsp;</label></div>
                    <textarea name="description" placeholder="  Una breve descripcion de su vehiculo"
                              required></textarea>
                </div>
                Selecciona una foto:<input type="file" name="fileName" required>
                <input type="hidden" name="category" value="Motor"/>
                <input type="hidden" name="userId" value="<%=user.getId()%>"/>
                <input type="hidden" name="sold" value="no"/>
            </div>
            <div class="save">
                <button type="submit">Guardar</button>
            </div>
        </form>
    </div>

    <div class="page-invisible" id="prop-form">
        <form action="/Save" method="POST" class="form" enctype="multipart/form-data">
            <div class="form-contents">
                <div class="tittle">
                    <div><label for="name">Título</label></div>
                    <input type="text" name="name" placeholder="  Titulo del anuncio" required/>
                </div>
                <div class="type">
                    <div><label for="type">Tipo</label></div>
                    <input type="text" name="extraInfo" placeholder="  Ej: Cochera, Cortijo" required/>
                </div>
                <!--<div class="suface">
                    <div><label for="suface">Superfície m2&emsp;</label></div>
                    <input type="text" id="suface" name="suface" placeholder="  m2" required/>
                </div>-->
                <div class="price">
                    <div><label for="price">Precio&emsp;</label></div>
                    <input style="width: 100%" name="price" placeholder="  Con cabeza..." required/>
                </div>
                <div class="state">
                    <div><label for="model">Estado&emsp;</label></div>
                    <input type="text" name="state" placeholder="  Ej: Obra nueva, Reformar..." required/>
                </div>
                <!--<div class="rent-sale">
                    <div><label for="rent-sale">Alquiler o Venta&emsp;</label></div>
                    <select name="rent-sale" id="rent-sale" required>
                        <option value="Alquiler">Alquiler</option>
                        <option value="Venta">Venta</option>
                    </select>
                </div>-->
                <div class="description">
                    <div><label for="description">Descripcion&emsp;</label></div>
                    <textarea name="description" placeholder="  Una breve descripcion de su propiedad"
                              required></textarea>
                </div>
                Selecciona una foto:<input type="file" name="fileName" required>
                <input type="hidden" name="category" value="Property"/>
                <input type="hidden" name="userId" value="<%=user.getId()%>"/>
                <input type="hidden" name="sold" value="no"/>
            </div>
            <div class="save">
                <button type="submit">Guardar</button>
            </div>
        </form>
    </div>

    <div class="page-visible" id="technology-form">
        <form action="/Save" method="POST" class="form" enctype="multipart/form-data">
            <div class="form-contents">
                <div class="tittle">
                    <div><label for="name">Título</label></div>
                    <input type="text" name="name" placeholder=" Titulo del anuncio">
                </div>
                <div class="type">
                    <div><label for="type">Tipo</label></div>
                    <input type="text" name="extraInfo" placeholder="  Ej: Portatil, Telefono" required>
                </div>
                <div class="price">
                    <div><label for="price">Precio&emsp;</label></div>
                    <input style="width: 100%" name="price" placeholder="  Con cabeza..." required>
                </div>
                <div class="state">
                    <div><label for="model">Estado&emsp;</label></div>
                    <input type="text" name="state" placeholder="  Ej: Como nuevo.." required>
                </div>
                <div class="description">
                    <div><label for="description">Descripcion&emsp;</label></div>
                    <textarea name="description" placeholder="  Una breve descripcion de su producto"
                              required></textarea>
                </div>
                Selecciona una foto:<input type="file" name="fileName">
                <input type="hidden" name="category" value="Informática"/>
                <input type="hidden" name="userId" value="<%=user.getId()%>"/>
                <input type="hidden" name="sold" value="no"/>
            </div>
            <div class="save">
                <button type="submit">Guardar</button>
            </div>
        </form>
    </div>

    <div class="page-invisible" id="fashion-form">
        <form action="/Save" method="POST" class="form" enctype="multipart/form-data">
            <div class="form-contents">
                <div class="tittle">
                    <div><label for="name">Título</label></div>
                    <input type="text" name="name" placeholder="  Titulo del anuncio" required/>
                </div>
                <div class="type">
                    <div><label for="type">Tipo</label></div>
                    <input type="text" name="extraInfo" placeholder="  Ej: Camiseta, Pantalones" required/>
                </div>
                <div class="price">
                    <div><label for="price">Precio&emsp;</label></div>
                    <input style="width: 100%" name="price" placeholder="  Con cabeza..." required/>
                </div>
                <div class="state">
                    <div><label for="model">Estado&emsp;</label></div>
                    <input type="text" name="state" placeholder="  Ej: Como nuevo.." required/>
                </div>
                <div class="description">
                    <div><label for="description">Descripcion&emsp;</label></div>
                    <textarea id="description" name="description"
                              placeholder="  Una breve descripcion de su producto"
                              required></textarea>
                </div>
                Selecciona una foto:<input type="file" name="fileName" required>
                <input type="hidden" name="category" value="Moda"/>
                <input type="hidden" name="userId" value="<%=user.getId()%>"/>
                <input type="hidden" name="sold" value="no"/>
            </div>
            <div class="save">
                <button type="submit">Guardar</button>
            </div>
        </form>
    </div>


    <div class="page-invisible" id="other-form">
        <form action="/Save" method="POST" class="form" enctype="multipart/form-data">
            <div class="form-contents">
                <div class="title">
                    <div><label for="name">Título</label></div>
                    <input type="text" name="name" placeholder="  Titulo del anuncio" required/>
                </div>
                <div class="type">
                    <div><label for="type">Tipo</label></div>
                    <input type="text" id="type" name="extraInfo" placeholder="  Ej: Servicios, Hogar..."
                           required/>
                </div>
                <div class="price">
                    <div><label for="model">Precio&emsp;</label></div>
                    <input style="width: 100%" id="price" name="price" placeholder="  Con cabeza..." required/>
                </div>
                <div class="description">
                    <div><label for="description">Descripcion&emsp;</label></div>
                    <textarea name="description" placeholder="  Una breve descripcion de su propiedad"
                              required></textarea>
                </div>
                Selecciona una foto:<input type="file" name="fileName" required>
                <input type="hidden" name="category" value="Other"/>
                <input type="hidden" name="userId" value="<%=user.getId()%>"/>
                <input type="hidden" name="sold" value="no"/>
            </div>
            <div class="save">
                <button type="submit">Guardar</button>
            </div>
        </form>
    </div>
</div>
<script language="javascript">
    function clickMotor() {
        document.getElementById("motor-form").removeAttribute("class");
        document.getElementById("prop-form").removeAttribute("class");
        document.getElementById("fashion-form").removeAttribute("class");
        document.getElementById("technology-form").removeAttribute("class");
        document.getElementById("other-form").removeAttribute("class");

        document.getElementById("motor-form").classList.add("page-visible");
        document.getElementById("prop-form").classList.add("page-invisible");
        document.getElementById("fashion-form").classList.add("page-invisible");
        document.getElementById("technology-form").classList.add("page-invisible");
        document.getElementById("other-form").classList.add("page-invisible");
    }

    function clickFashion() {
        document.getElementById("motor-form").removeAttribute("class");
        document.getElementById("prop-form").removeAttribute("class");
        document.getElementById("fashion-form").removeAttribute("class");
        document.getElementById("technology-form").removeAttribute("class");
        document.getElementById("other-form").removeAttribute("class");

        document.getElementById("motor-form").classList.add("page-invisible");
        document.getElementById("prop-form").classList.add("page-invisible");
        document.getElementById("fashion-form").classList.add("page-visible");
        document.getElementById("technology-form").classList.add("page-invisible");
        document.getElementById("other-form").classList.add("page-invisible");
    }

    function clickProp() {
        document.getElementById("motor-form").removeAttribute("class");
        document.getElementById("prop-form").removeAttribute("class");
        document.getElementById("fashion-form").removeAttribute("class");
        document.getElementById("technology-form").removeAttribute("class");
        document.getElementById("other-form").removeAttribute("class");


        document.getElementById("motor-form").classList.add("page-invisible");
        document.getElementById("prop-form").classList.add("page-visible");
        document.getElementById("fashion-form").classList.add("page-invisible");
        document.getElementById("technology-form").classList.add("page-invisible");
        document.getElementById("other-form").classList.add("page-invisible");
    }

    function clickOther() {
        document.getElementById("motor-form").removeAttribute("class");
        document.getElementById("prop-form").removeAttribute("class");
        document.getElementById("fashion-form").removeAttribute("class");
        document.getElementById("technology-form").removeAttribute("class");
        document.getElementById("other-form").removeAttribute("class");

        document.getElementById("motor-form").classList.add("page-invisible");
        document.getElementById("prop-form").classList.add("page-invisible");
        document.getElementById("fashion-form").classList.add("page-invisible");
        document.getElementById("technology-form").classList.add("page-invisible");
        document.getElementById("other-form").classList.add("page-visible");
    }

    function clickTechnology() {
        document.getElementById("motor-form").removeAttribute("class");
        document.getElementById("prop-form").removeAttribute("class");
        document.getElementById("fashion-form").removeAttribute("class");
        document.getElementById("technology-form").removeAttribute("class");
        document.getElementById("other-form").removeAttribute("class");

        document.getElementById("motor-form").classList.add("page-invisible");
        document.getElementById("prop-form").classList.add("page-invisible");
        document.getElementById("fashion-form").classList.add("page-invisible");
        document.getElementById("technology-form").classList.add("page-visible");
        document.getElementById("other-form").classList.add("page-invisible");
    }
</script>
</body>
</html>
