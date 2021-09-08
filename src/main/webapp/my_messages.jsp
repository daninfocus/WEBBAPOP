<%@ page import="Modelo.Message" %>
<%@ page import="Modelo.GestionAPP" %>
<%@ page import="Modelo.Usuario" %>
<%@ page import="Modelo.Producto" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 27/08/2021
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="./resources/css/my_messages.css"/>
    <link href="./resources/FontAwesome/css/all.css" rel="stylesheet"/>
    <title>Document</title>
</head>
<body>

<%
    GestionAPP gestionAPP = new GestionAPP();
    Usuario loggedInUser = gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString());




%>
<div class="globalContainer">
    <!---margin: 60px 0 500px 100px;-->
    <h2>Bandeja de Entrada</h2>
    <h4>
        Aquí podrás ver tus mensajes.
    </h4>
    <div class="containerMessage">
        <div class="containerList">
            <%
                for (Integer product_ids : gestion.getAllChats(loggedInUser.getId())) {
                    ArrayList<Message> messages = gestion.getAllMessages(loggedInUser.getId(), product_ids);

                    Producto producto = gestion.getProductoPorID(product_ids);
                    if(producto!=null) {
                        Usuario usuarioOtro;
                        if (loggedInUser.getId() == producto.getIdUsuario()) {
                            usuarioOtro = gestion.getUsuarioPorId(messages.get(0).getID_User_Sender());
                        } else {
                            usuarioOtro = gestion.getUsuarioPorId(producto.getIdUsuario());
                        }

                        Message lastMessage = messages.get(messages.size() - 1);

                        String tick = "";
                        if (loggedInUser.getId() == lastMessage.getID_User_Sender()) {
                            if (lastMessage.isMessage_Read() == 1) {
                                tick = "  <i style=\"color:#4e9133\" class=\"fas fa-check-double\"></i>";
                            } else {
                                tick = "  <i style=\"color:#404040\" class=\"fas fa-check\"></i>";
                            }
                        }

                        out.print("<a class=\"aList\" href=\"/Profile?Product_ID=" + product_ids + "\"><div class=\"listMessages\">" +
                                "    <img\n" +
                                "            class=\"img\"\n" +
                                "            src=\"image.jsp?imgID="+product_ids+"\"\n" +
                                "    />\n" +
                                "   <div class=\"data\">\n" +
                                "       <div class=\"top\">\n" +
                                "           <div class=\"name\">" + usuarioOtro.getNombre() + "</div>\n" +
                                "           <div class=\"dateLastMessage\">" + (messages.size() > 1 ? messages.get(messages.size() - 1).getSent_Date() : "") + "</div>\n" +
                                "       </div>\n" +
                                "       <div class=\"productName\">" + gestion.getProductoPorID(product_ids).getNombre() + "</div>\n" +
                                "       <div class=\"lastMessage\">" + lastMessage.getMessage() + " " + (messages.size() > 1 ? tick : "") + "</div>\n" +
                                "   </div>" +
                                "</div></a>");

                    }
                }
            %>

        </div>
        <div class="messageContainer">
            <%if(request.getParameter("Product_ID")!=null) {
                int product_id = Integer.parseInt(request.getParameter("Product_ID").toString());
                Producto producto = gestion.getProductoPorID(product_id);
                Usuario usuarioVendedor = gestion.getUsuarioPorId(producto.getIdUsuario());
                out.print("<div class=\"user\">\n" +
                        usuarioVendedor.getNombre() +
                        "                <a class=\"buttonDeleteConvo\" style=\"color:black;text-align: right;\" href=\"#popup1\"><i class=\"fas fa-ellipsis-v\"></i></a>\n" +
                        "            </div>");



                }
            %>
            <div id="popup1" class="overlay">
                <div class="popup">
                    <h2>Opciones</h2>
                    <a class="close" href="#">&times;</a>
                    <div class="popUpContent">
                        <ul>
                            <a class="deleteConvo" href="/deleteConvo">Borrar conversacion</a>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="messages1">
                <%
                    if (request.getParameter("Product_ID") != null) {

                        int product_id = Integer.parseInt(request.getParameter("Product_ID").toString());
                        Producto producto = gestion.getProductoPorID(product_id);


                        ArrayList<Message> messages = gestion.getAllMessages(loggedInUser.getId(), product_id);
                        if(messages.size()>1) {
                            for (Message message : messages) {

                                if (message.getID_User_Reciever() == loggedInUser.getId()) {
                                    out.print("<p class=\"messageContentOther\">" + message.getMessage() + "</p><br>");
                                    message.setMessage_Read(1);
                                    gestion.updateMessage(message);
                                } else {
                                    if (message.isMessage_Read() == 1) {
                                        out.print((message.getMessage().equals("")?"":"<p class=\"messageContent\">" + message.getMessage() + "  </p><i style=\"color:#4e9133\" class=\"fas fa-check-double\"></i><br>"));
                                    } else {
                                        out.print((message.getMessage().equals("")?"":"<p class=\"messageContent\">" + message.getMessage() + "  </p><i style=\"color:#262626\" class=\"fas fa-check\"></i><br>"));
                                    }
                                }

                            }
                        }
                        out.print("</div><div class=\"formDiv\"><form class=\"messageForm\" action=\"/saveMessage\" method=\"post\">\n" +
                                "                <label for=\"message-box\" type=\"hidden\"></label>\n" +
                                "                <textarea maxlength=\"150\" id=\"message-box\" name=\"message\" placeholder=\"Escribe algo aqui, maximo 150 characteres\"></textarea>\n" +
                                "                <input type=\"hidden\" name=\"userId\" value=" + gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString()).getId() + ">");
                        if (request.getParameter("Product_ID") != null) {
                            out.print("<input type=\"hidden\" name=\"productId\" value=" + request.getParameter("Product_ID") + ">");
                        }

                        if (request.getParameter("Product_ID") != null) {
                            if (producto.getIdUsuario() == loggedInUser.getId()) {


                                out.print("<input type=\"hidden\" name=\"idUserReciever\" value=" + messages.get(0).getID_User_Sender() + ">");
                            } else {
                                out.print("<input type=\"hidden\" name=\"idUserReciever\" value=" + producto.getIdUsuario() + ">");
                            }
                        }
                        out.print("<button type=\"submit\" class=\"send\"><i class=\"far fa-paper-plane\"></i></button>" +
                                "</form>");

                    } else {
                        out.print("<p style=\"text-align: center;margin-top:50px; color:white;font-size:25px\" ><i class=\"far fa-hand-point-left\"></i> Selecciona un chat <i class=\"fas fa-exclamation\"></i></p>");
                    }
                %>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>
    $("#message-box").on("keydown", function () {
        this.style.height = "1px";
        this.style.height = this.scrollHeight + "px";
    });
</script>
</body>
</html>
