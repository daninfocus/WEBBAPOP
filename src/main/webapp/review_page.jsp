<%@ page import="Modelo.Producto" %>
<%@ page import="Modelo.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Modelo.GestionAPP" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="Modelo.Message" %>
<%@ page import="javax.mail.search.BodyTerm" %><%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 09/09/2021
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link
            rel="shortcut icon"
            type="img/favicon.ico"
            href="./resources/img/logo512circle.png"
    />
    <link href="./resources/FontAwesome/css/all.css" rel="stylesheet"/>
    <!--load all styles -->
    <link rel="stylesheet" href="./resources/css/sell.css"/>
    <link rel="apple-touch-icon" href="./resources/img/logo192.png"/>
    <link
            href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
            rel="stylesheet"
            id="bootstrap-css"
    />

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Wallapop | Vender</title>
</head>
<body>
<div id="popup1" class="overlay">
    <div class="popup">
        <h2>¡Enhorabuena!</h2>
        <%
            if (request.getParameter("OpenChats") == null) {
                out.print("<h5>Has vendido un producto</h5>");
            } else {
                out.print("<h5>Has comprado un producto</h5>");
            }
            GestionAPP gestion = (GestionAPP) session.getAttribute("gestion");
            Usuario loggedInUser = gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString());
            int product_id = Integer.parseInt(request.getParameter("Product_ID"));

            int idLoggedInUser = loggedInUser.getId();

            ArrayList<Message> messages = gestion.getAllMessages(idLoggedInUser, product_id);


            if (((request.getAttribute("OpenChats") != null && request.getAttribute("OpenChats").equals("Yes")) || request.getParameter("OpenChats") != null) && messages.size() > 1) {

                if (request.getParameter("OpenChats") != null) {
                    out.print("<form action=\"/Sold?FormComplete=yes&Tipo=Venta\" method=\"post\">");
                } else {
                    out.print("<form action=\"/Sold?FormComplete=yes\" method=\"post\">");
                }
        %>


        <input type="hidden" value="<%=request.getParameter("Product_ID")%>" name="Product_ID"/>
        <div class="popUpContentHasMessages">
            <div class="userParent">
                <ul></ul>
                <%
                    if (request.getParameter("OpenChats") == null) {
                        out.print("<label for=\"listaUsuario\">Usuario al que se lo has vendido</label>");
                    } else {
                        out.print("<label for=\"listaUsuario\">Usuario al que se lo has comprado</label>");
                    }
                %>

                <select required class="form-control" id="listaUsuario">

                    <%

                        Usuario otroUsuario = null;
                        for (Message message : messages) {
                            int idOtroUsuario = message.getID_User_Reciever();
                            if (idLoggedInUser != idOtroUsuario) {
                                otroUsuario = gestion.getUsuarioPorId(idOtroUsuario);
                                out.print("<option>" + otroUsuario.getNombre() +"  "+ otroUsuario.getApellidos());
                                out.print("<input type=\"hidden\" name=\"buyerEmail\" value=\"" + otroUsuario.getEmail() + "\"");
                                break;
                            }
                        }


                    %>
                </select>
            </div>

            <div class="starsContainer">
                <h3>Deja una valoracion</h3>
                <div id="stars"></div>
                <div class="qty mt-5">
                    <span class="minus bg-dark" id="minus">-</span>
                    <label for="points"></label><input type="number" name="points" id="points" class="count"
                                                       value="1" required/>
                    <span class="plus bg-dark" id="plus">+</span>
                </div>
            </div>

            <%if (request.getParameter("OpenChats") == null) {%>
            <div class="price">
                <label for="price"></label><input type="number" name="price" id="price" placeholder="Precio Final"
                                                  required/>
            </div>
            <%
                } else {
                    out.print("<input type=\"hidden\" name=\"price\" id=\"price\" value=\"0\"/>");
                }
            %>

            <div class="review">
                    <textarea
                            id="reviewContent"
                            name="reviewContent"
                            placeholder="Deje una breve reseña de este comprador" required
                    ></textarea>
            </div>

            <button type="submit" onclick="return confirm('Estas seguro de marcarlo como vendido?')"
                    class="btn btn-danger btn-sm">Marcar como vendido</button>
        </div>
        </form>
        <%
            }
        %>
        <hr/>
        <div class="popUpContent">
            <form action="/Sold?Outside=yes" method="post">
                <input type="hidden" value="<%=request.getParameter("Product_ID")%>" name="Product_ID"/>
                <button
                        type="submit"
                        class="btn btn-danger btn-sm"
                        onclick="return confirm('Estas seguro de marcarlo como vendido?')"
                >
                    Lo he vendido fuera de Wallapop
                </button>
            </form>
        </div>
    </div>
</div>


<script>


    let counterMinusElem = document.getElementById('minus');
    let counterPlusElem = document.getElementById('plus');

    let count = 1;

    updateDisplay();

    counterPlusElem.addEventListener("click", () => {
        if (count < 5) {
            count++;
            console.log(count);
            updateDisplay();
        }
    });

    counterMinusElem.addEventListener("click", () => {
        if (count > 1) {
            count--;
            console.log(count);
            updateDisplay();
        }
    });

    function updateDisplay() {
        if (count == 1) {
            document.getElementById("stars").textContent = "⭐";
        }
        if (count == 2) {
            document.getElementById("stars").textContent = "⭐⭐";
        }
        if (count == 3) {
            document.getElementById("stars").textContent = "⭐⭐⭐";
        }
        if (count == 4) {
            document.getElementById("stars").textContent = "⭐⭐⭐⭐";
        }
        if (count == 5) {
            document.getElementById("stars").textContent = "⭐⭐⭐⭐⭐";
        }
        document.getElementById('points').value = count;
    }
</script>
</body>
</html>
