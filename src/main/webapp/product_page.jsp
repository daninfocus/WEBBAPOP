<%@ page import="Modelo.GestionAPP" %>
<%@ page import="Modelo.Producto" %>
<%@ page import="Modelo.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 04/09/2021
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
  <link
          rel="shortcut icon"
          type="img/favicon.ico"
          href="resources/img/logo512circle.png"
  />
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="./resources/css/product_page.css" />
  <link href="./resources/FontAwesome/css/all.css" rel="stylesheet" />
  <title>Webbapop | Product</title>
</head>
<body>
<div class="header">
  <!-----------------------------------------------------------------------------------------------------------------SearchBAR -->
  <a href="${pageContext.request.contextPath}/Home" class="logo"><i class="fab fa-weebly"></i>ebbaPop</a
  ><!--Webbapop logo -->

  <div class="wrap">
    <!-----------------------------------------------------------SearchBAR -->
    <div class="search">
      <input type="text" class="searchTerm" placeholder=" Search" />
      <button type="submit" class="searchButton">
        <i class="fa fa-search"></i>
      </button>
    </div>
  </div>

  <div class="header-right">
    <!-----------------------------------------Header right options -->
    <%
      if(request.getSession().getAttribute("loggedInUser")==null){

      }else{
        out.print("<a class=\"option1\" href=\"Profile\"><i  class=\"far fa-user fa-lg\"></i>&nbsp;&nbsp;"+ request.getSession().getAttribute("name")+"</a>");
        out.print("<a class=\"option2\" href=\"/Profile?newProduct=true\"><i class=\"fa fa-plus-square\" ></i><div class=\"addProduct\">&nbsp;&nbsp;Subir un producto</div></a>");

        session.setAttribute("user", request.getAttribute("name"));      //set attribute in session
        session.setAttribute("email", request.getAttribute("email"));
      }
    %>
    <!--<a href="hello-servlet">Hello Servlet</a>-->
  </div>
</div>

<%
  int idProd = Integer.parseInt(request.getParameter("Product_ID"));
  GestionAPP gestionAPP = new GestionAPP();
  Producto product = gestionAPP.getProductoPorID(idProd);
  Usuario loggedInUser = gestionAPP.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString());
  Usuario seller = gestionAPP.getUsuarioPorId(product.getIdUsuario());
  if(product.getIdUsuario()!=loggedInUser.getId()){
    out.print("\n" +
            "<div class=\"containerProduct\">\n" +
            "  <div class=\"userContainer\">\n" +
            "    <div class=\"userName\">\n" +
            "      <i class=\"far fa-user\"></i>\n" +
            "      <h4>"+seller.getNombre()+"</h4>\n" +
            "    </div>\n" +
            "    <div class=\"rating\">\n" +
            "      <div class=\"stars\">"+ seller.getNotaMedia()+"</div>\n" +
            "      <div class=\"numRatings\">"+seller.getNotaMedia()+"</div>\n" +
            "    </div>\n" +
            "   <a class=\"add\" href=\"/Profile?Product_ID="+product.getid()+"\"><button class=\"addButton\">Add&nbsp;&nbsp;<i class=\"fa fa-heart\"></i></button></a>\n" +
            "   <a class=\"message\" href=\"/Profile?Product_ID="+product.getid()+"\"><button class=\"messageButton\">Chat&nbsp;&nbsp;<i class=\"far fa-comment-dots\"></i></button></a>\n" +
            "  </div>\n" +
            "  <div class=\"image\">\n" +
            "    <img\n" +
            "            class=\"img\"\n" +
            "            src=\"image.jsp?imgID="+idProd+"\"\n" +
            "    />\n" +
            "  </div>\n" +
            "  <div class=\"productInfo\">\n" +
            "    <h2 class=\"price\">\n" +
            "      "+product.getPrecio()+" <i class=\"fa fa-euro-sign\" aria-hidden=\"true\"></i>\n" +
            "    </h2>\n" +
            "    <h1 class=\"productName\">"+product.getNombre()+"</h1>\n" +
            "    <hr />\n" +
            "    <div class=\"subInfo\">\n" +
            "      <div class=\"category\">"+product.getCategoria()+"</div>\n" +
            "      <div class=\"state\">Estado: "+product.getEstado()+"</div>\n" +
            "    </div>\n" +
            "    <hr />\n" +
            "    <div class=\"description\">\n"
            +product.getDescripcion()+
            "    </div>\n" +
            "    <hr />\n" +
            "    <div class=\"bottomInfo\">\n" +
            "      <div class=\"date\">"+product.getFecha()+"</div>\n" +
            "      <div class=\"rightShit\">\n" +
            "        <div class=\"views\">218<i class=\"far fa-eye\"></i></div>\n" +
            "        <div class=\"saves\">2<i class=\"far fa-heart\"></i></div>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</div>");
  }else{
    out.print("\n" +
            "<div class=\"containerProduct\">\n" +
            "  <div class=\"userContainer\">\n" +
            "    <div class=\"userName\">\n" +
            "      <i class=\"far fa-user\"></i>\n" +
            "      <h4>"+seller.getNombre()+"</h4>\n" +
            "    </div>\n" +
            "      <div class=\"buttons\">\n" +
            (product.getVendido()==0?"          <button onclick=\"window.location.href='/Sold?Product_ID="+product.getid()+"';\" type=\"button\" title=\"Marcar como vendido\" class=\"sell\">\n" +
                    "              <i class=\"far fa-handshake\"></i>\n" +
                    "          </button>\n" +
                    "          <button onclick=\"window.location.href='/Reserved?Product_ID="+product.getid()+"';\" type=\"button\" title=\"Marcar como reservado\" class=\"reserve\">\n" +
                    "              <i class=\"far fa-bookmark\"></i>\n" +
                    "          </button>\n" +
                    "          <button onclick=\"window.location.href='/Edit?Product_ID="+product.getid()+"';\" type=\"button\" title=\"Editar\"class=\"edit\"><i class=\"far fa-edit\"></i></button>\n"
            :"")

            +

            "          <button onclick=\"window.location.href='/Delete?Product_ID="+product.getid()+"';\" type=\"button\" title=\"BORRAR\" class=\"delete\">\n" +
            "              <i class=\"far fa-trash-alt\"></i>\n" +
            "          </button>\n" +
            "      </div>\n" +
            "  </div>\n" +
            "  <div class=\"image\">\n" +
            "    <img\n" +
            "            class=\"img\"\n" +
            "            src=\"image.jsp?imgID="+idProd+"\"\n" +
            "    />\n" +
            "  </div>\n" +
            "  <div class=\"productInfo\">\n" +
            "    <h2 class=\"price\">\n" +
            "      "+product.getPrecio()+" <i class=\"fa fa-euro-sign\" aria-hidden=\"true\"></i>\n" +
            "    </h2>\n" +
            "    <h1 class=\"productName\">"+product.getNombre()+"</h1>\n" +
            "    <hr />\n" +
            "    <div class=\"subInfo\">\n" +
            "      <div class=\"category\">"+product.getCategoria()+"</div>\n" +
            "      <div class=\"state\">Estado: "+product.getEstado()+"</div>\n" +
            "    </div>\n" +
            "    <hr />\n" +
            "    <div class=\"description\">\n"
            +product.getDescripcion()+
            "    </div>\n" +
            "    <hr />\n" +
            "    <div class=\"bottomInfo\">\n" +
            "      <div class=\"date\">"+product.getFecha()+"</div>\n" +
            "      <div class=\"rightShit\">\n" +
            "        <div class=\"views\">218<i class=\"far fa-eye\"></i></div>\n" +
            "        <div class=\"saves\">2<i class=\"far fa-heart\"></i></div>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</div>");
  }

%>
</body>
</html>
