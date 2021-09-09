<%@ page import="Modelo.GestionAPP" %>
<%@ page import="Modelo.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 21/08/2021
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  GestionAPP gestion = (GestionAPP)session.getAttribute("gestion");
  Usuario user = gestion.getUsuarioPorEmail(request.getSession().getAttribute("loggedInUser").toString());

%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="./resources/css/my_profile.css" />
  <link href="./resources/FontAwesome/css/all.css" rel="stylesheet" />
  <title>Webbapop | Products</title>
</head>
<body>
<div class="parent">
  <div class="titlecontainer">
    <h2 class="title">Tu perfil</h2>
    <a class="logout" href="/Logout">Cerrar Sesion</a>
  </div>
  <h4 class="desc">Aquí podrás ver y editar los datos de tu perfil</h4>
  <div class="listProfile1">
    <h4 class="cat1">Información personal</h4>
    <div class="container">
      <label for="email" class="email">Email</label>
      <div class="emailProfile">
        <input type="email" placeholder="email" id="email" name="email" value="<%
        out.print(user.getEmail());

        %>">
      </div>
      <label for="date">Fecha de nacimiento</label>
      <div class="date"><input type="date" id="date" name="date" value="<%
        String date = user.getFecha_nacimiento();
        String day = date.substring(0,2);
        String month = date.substring(3,5);
        String year = date.substring(6,10);
        String formattedDate= year+"-"+month+"-"+day;
        out.print(formattedDate);

        %>"></div>


      <label class="sexo">Sexo</label>
      <div class="wrapper">

        <input type="radio" name="sexo" id="option-1" <%
          if(user.getSexo().equals("Hombre")){
            out.print("checked");
          }
        %>/>

        <label for="option-1" class="option option-1">
          <div class="dot"></div>
          <span>&nbsp;Hombre</span>
        </label>

        <input type="radio" name="sexo" id="option-2"  <%
          if(user.getSexo().equals("Mujer")){
            out.print("checked");
          }
        %> />

        <label for="option-2" class="option option-2">
          <div class="dot"></div>
          <span>&nbsp;Mujer</span>
        </label>

        <input type="radio" name="sexo" id="option-3" <%
          if(user.getSexo().equals("Otro")){
            out.print("checked");
          }
        %>/>

        <label for="option-3" class="option option-3">
          <div class="dot"></div>
          <span>&nbsp;Otro</span>
        </label>
      </div>
      <div class="save"><button type="submit">Guardar</button></div>
    </div>
  </div>
  <div class="listProfile2">
    <h4 class="cat2">Información publica</h4>
    <div class="container2">
      <label for="username" class="username">Nombre</label>
      <div class="usernameProfile">
        <input type="text" placeholder="usuario" id="username" name="username" value="<%

        out.print(user.getNombre());

        %>">
      </div>
      <label for="surname">Apellidos</label>
      <div class="surname"><input type="text" placeholder="Apellidos" id="surname" name="surname" value="<%

        out.print(user.getApellidos());

        %>"></div>


      <label for="location">Ubicación</label>
      <div class="location">
        <input type="text" id="location" name="location" placeholder="Ubicación"value="<%

        out.print(user.getDireccion());

        %>">
      </div>
      <div class="save2"><button type="submit">Guardar</button></div>
    </div>
  </div>
</div>
</body>
</html>


