<%@ page import="java.util.ArrayList" %>
<%@ page import="Modelo.*" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %><%--
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
    GestionAPP gestion = (GestionAPP) session.getAttribute("gestion");
    Usuario loggedInUser = gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString());
    int chat_id;
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
                ArrayList<Chat> chats = gestion.getUserChats(loggedInUser.getId());
                for (Chat chat : chats) {
                    ArrayList<Chat> chatUsers = gestion.getChat(chat.getID_Chat());
                    int otherUserID = 0;
                    for (Chat chatUser : chatUsers) {
                        if (chatUser.getID_User() != loggedInUser.getId()) {
                            otherUserID = chatUser.getID_User();
                        }
                    }

                    ArrayList<Message> messages = gestion.getChatMessages(chat.getID_Chat());
                    Producto producto = gestion.getProductoPorID(chat.getID_Product());

                    if (producto != null) {
                        int Product_ID = chat.getID_Product();
                        Usuario usuarioOtro;
                        if (loggedInUser.getId() == producto.getIdUsuario()) {
                            usuarioOtro = gestion.getUsuarioPorId(messages.get(0).getID_User_Sender());
                        } else {
                            usuarioOtro = gestion.getUsuarioPorId(producto.getIdUsuario());
                        }

                        Message lastMessage = new Message(0, 0, 0, 0, 0, "", "", "", 0, 0);

                        for (int i = messages.size() - 1; i > 0; i--) {
                            if (messages.get(i).isMessage_Deleted() == 0) {
                                lastMessage = messages.get(i);
                                break;
                            }
                        }

                        String tick = "";
                        if (lastMessage != null && loggedInUser.getId() == lastMessage.getID_User_Sender()) {
                            if (lastMessage.isMessage_Read() == 1) {
                                tick = "  <i style=\"color:#4e9133\" class=\"fas fa-check-double\"></i>";
                            } else {
                                tick = "  <i style=\"color:#404040\" class=\"fas fa-check\"></i>";
                            }
                        }
                        if (request.getParameter("Product_ID") != null) {
                            if (Integer.parseInt(request.getParameter("Product_ID")) == producto.getid()) {
                                out.print("<a class=\"aList\" href=\"/Profile?Option=messages&Chat_ID=" + chat.getID_Chat() + "\"><div style=\"background-color: rgb(206 206 206);\" class=\"listMessages\">");
                            } else {
                                out.print("<a class=\"aList\" href=\"/Profile?Option=messages&Chat_ID=" + chat.getID_Chat() + "\"><div style=\"background-color: rgb(100, 100, 100);\" class=\"listMessages\">");
                            }
                        } else {
                            out.print("<a class=\"aList\" href=\"/Profile?Option=messages&Chat_ID=" + chat.getID_Chat() + "\"><div style=\"background-color: rgb(100, 100, 100);\" class=\"listMessages\">");
                        }
                        if (gestion.getProductoPorID(Product_ID).getVendido() == 1) { //SOLD
                            out.print("   <div class=\"imgContainer\">" +
                                    "       <img\n" +
                                    "               class=\"img\"\n" +
                                    "               src=\"image.jsp?imgID=" +Product_ID+ "\"\n" +
                                    "       />\n" +
                                    "       <div class=\"sold\"> <i class=\"far fa-handshake\"></i></div>" +
                                    "   </div>" +
                                    "   <div class=\"data\">\n" +
                                    "       <div class=\"top\">\n" +
                                    "           <div class=\"name\">" + usuarioOtro.getNombre() + "</div>\n" +
                                    "           <div class=\"dateLastMessage\">" + (messages.size() > 1 ? messages.get(messages.size() - 1).getSent_Date() : "") + "</div>\n" +
                                    "       </div>\n" +
                                    "       <div class=\"productName\">" + gestion.getProductoPorID(Product_ID).getNombre() + "</div>\n" +
                                    "       <div class=\"lastMessage\">" + lastMessage.getMessage() + " " + (messages.size() > 1 ? tick : "") + "</div>\n" +
                                    "   </div>" +
                                    "</div></a>");
                        } else {
                            if (gestion.getProductoPorID(Product_ID).getReserved() == 1) {//RESERVED
                                out.print("   <div class=\"imgContainer\">" +
                                        "       <img\n" +
                                        "               class=\"img\"\n" +
                                        "               src=\"image.jsp?imgID=" + Product_ID + "\"\n" +
                                        "       />\n" +
                                        "       <div class=\"reserved\"> <i class=\"far fa-bookmark\"></i></div>" +
                                        "   </div>" +
                                        "   <div class=\"data\">\n" +
                                        "       <div class=\"top\">\n" +
                                        "           <div class=\"name\">" + usuarioOtro.getNombre() + "</div>\n" +
                                        "           <div class=\"dateLastMessage\">" + (messages.size() > 1 ? messages.get(messages.size() - 1).getSent_Date() : "") + "</div>\n" +
                                        "       </div>\n" +
                                        "       <div class=\"productName\">" + gestion.getProductoPorID(Product_ID).getNombre() + "</div>\n" +
                                        "       <div class=\"lastMessage\">" + lastMessage.getMessage() + " " + (messages.size() > 1 ? tick : "") + "</div>\n" +
                                        "   </div>" +
                                        "</div></a>");
                            } else {//NORMAL PRODUCT
                                out.print("       <img\n" +
                                        "               class=\"img\"\n" +
                                        "               src=\"image.jsp?imgID=" + Product_ID + "\"\n" +
                                        "       />\n" +
                                        "   <div class=\"data\">\n" +
                                        "       <div class=\"top\">\n" +
                                        "           <div class=\"name\">" + usuarioOtro.getNombre() + "</div>\n" +
                                        "           <div class=\"dateLastMessage\">" + (messages.size() > 1 ? messages.get(messages.size() - 1).getSent_Date() : "") + "</div>\n" +
                                        "       </div>\n" +
                                        "       <div class=\"productName\">" + gestion.getProductoPorID(Product_ID).getNombre() + "</div>\n" +
                                        "       <div class=\"lastMessage\">" + lastMessage.getMessage() + " " + (messages.size() > 1 ? tick : "") + "</div>\n" +
                                        "   </div>" +
                                        "</div></a>");
                            }
                        }
                    }
                }
            %>

        </div>
        <div class="messageContainer">
            <%
                if (request.getParameter("Chat_ID") != null) {
                    chat_id = Integer.parseInt(request.getParameter("Chat_ID").toString());

                    Producto producto = gestion.getProductoPorChatId(chat_id);

                    Usuario usuarioOtro = gestion.getOtherUserFromChat(loggedInUser.getId(),chat_id);

                    ArrayList<Message> messages = gestion.getChatMessages(chat_id);

                    out.print("<div class=\"user\">\n" +
                            usuarioOtro.getNombre() +
                            "      <a class=\"buttonDeleteConvo\" style=\"color:black;\" href=\"#popup1\"><i class=\"fas fa-ellipsis-v\"></i></a>\n" +
                            "  </div>");


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

                    ArrayList<Chat> chats2 = gestion.getUserChats(loggedInUser.getId());

                    if (request.getParameter("Chat_ID") != null) {

                        chat_id = Integer.parseInt(request.getParameter("Chat_ID").toString());
                        Producto producto = gestion.getProductoPorID(chat_id);

                        Usuario usuarioOtro = gestion.getOtherUserFromChat(loggedInUser.getId(),chat_id);

                        ArrayList<Message> messages = gestion.getChatMessages(chat_id);

                        if (messages.size() >=1) {
                            for (Message message : messages) {
                                if (message.getID_User_Reciever() == loggedInUser.getId() && message.isMessage_Deleted() == 0) {
                                    out.print("<p class=\"messageContentOther\">" + message.getMessage() + "</p><br>");
                                    if (message.isMessage_Read() == 0) {
                                        message.setMessage_Read(1);
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

                                        Calendar cal = new GregorianCalendar();
                                        message.setRecieved_Date(sdf.format(cal.getTime()));
                                        gestion.updateMessage(message);
                                    }
                                } else {
                                    if (message.getID_User_Sender() == loggedInUser.getId() && message.isMessage_Deleted() == 0) {
                                        if (message.isMessage_Read() == 1) {
                                            out.print((message.getMessage().equals("") ? "" : "<a href=\"/DeleteMessage?ID=" + message.getID_Message() + "\"> <i class=\"fa fa-trash\" aria-hidden=\"true\"></i></a><p class=\"messageContent\">" + message.getMessage() + "  </p><i style=\"color:#4e9133\" class=\"fas fa-check-double\"></i><br>"));
                                        } else {
                                            out.print((message.getMessage().equals("") ? "" : "<a href=\"/DeleteMessage?ID=" + message.getID_Message() + "\"> <i class=\"fa fa-trash\" aria-hidden=\"true\"></i></a><p class=\"messageContent\">" + message.getMessage() + "  </p><i style=\"color:#262626\" class=\"fas fa-check\"></i><br>"));
                                        }
                                    }
                                }

                            }
                        }
                        out.print("</div><div class=\"formDiv\"><form class=\"messageForm\" action=\"/saveMessage\" method=\"post\">\n" +
                                "                <label for=\"message-box\" type=\"hidden\"></label>\n" +
                                "                <textarea maxlength=\"150\" id=\"message-box\" name=\"message\" placeholder=\"Escribe algo aqui, maximo 150 characteres\"></textarea>\n" +
                                "                <input type=\"hidden\" name=\"userId\" value=" + gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString()).getId() + ">");
                        if (request.getParameter("Chat_ID") != null) {
                            chat_id = Integer.parseInt(request.getParameter("Chat_ID").toString());

                            Producto productoAux = gestion.getProductoPorChatId(chat_id);
                            out.print("<input type=\"hidden\" name=\"productId\" value=" + productoAux.getid() + ">");
                        }

                        if (request.getParameter("Chat_ID") != null) {
                            out.print("<input type=\"hidden\" name=\"idUserReciever\" value=" +gestion.getOtherUserFromChat(loggedInUser.getId(), chat_id).getId() + ">"+
                                    "<input type=\"hidden\" name=\"Chat_ID\" value=" +chat_id + ">"
                            );
                        }
                        out.print("<button type=\"submit\" class=\"send\"><i class=\"far fa-paper-plane\"></i></button>" +
                                "</form>");

                    } else {

                        if (gestion.getUserChats(loggedInUser.getId())==null) {
                            out.print("<p style=\"text-align: center;margin-top:50px; color:white;font-size:25px\" >No tienes chats abiertos <i style=\"position: inherit;right: inherit;margin-top: inherit;\" class=\"fas fa-exclamation \"></i></p>");
                        } else {
                            out.print("<p style=\"text-align: center;margin-top:50px; color:white;font-size:25px\" ><i style=\"position: inherit;right: inherit;margin-top: inherit;\" class=\"far fa-hand-point-left excl\"></i> Selecciona un chat <i style=\"position: inherit;right: inherit;margin-top: inherit;\" class=\"fas fa-exclamation \"></i></p>");
                        }
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
