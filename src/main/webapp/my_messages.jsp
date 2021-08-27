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
<%
    GestionAPP gestion = new GestionAPP();
    Usuario loggedInUser = gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString());



%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="./resources/css/my_messages.css" />
    <link href="./resources/FontAwesome/css/all.css" rel="stylesheet" />
    <title>Document</title>
</head>
<body>
<div class="globalContainer">
    <!---margin: 60px 0 500px 100px;-->
    <h2>Bandeja de Entrada</h2>
    <div class="containerMessage">
        <div class="containerList">


                    <%




                        for (Integer product_id : gestion.getAllChats(loggedInUser.getId())) {
                            Producto producto = gestion.getProductoPorID(product_id);
                            Usuario usuarioOtro = gestion.getUsuarioPorId(producto.getIdUsuario());

                            ArrayList<Message> messages = gestion.getAllMessages(loggedInUser.getId(), product_id);


                            out.print("<a class=\"aList\" href=\"/Profile?Product_ID="+product_id+"\"><div class=\"listMessages\">" +
                                "   <img\n" +
                                "       class=\"img\"\n" +
                                "       src=\"http://mistillas.cl/wp-content/uploads/2018/04/Nike-Epic-React-Flyknit-%E2%80%9CPearl-Pink%E2%80%9D-01.jpg\"\n" +
                                "       />\n" +
                                "   <div class=\"data\">\n" +
                                "       <div class=\"top\">\n" +
                                "           <div class=\"name\">"+usuarioOtro.getNombre()+"</div>\n" +
                                "           <div class=\"dateLastMessage\">"+messages.get(messages.size()-1).getSent_Date()+"</div>\n" +
                                "       </div>\n" +
                                "       <div class=\"productName\">"+gestion.getProductoPorID(product_id).getNombre()+"</div>\n" +
                                "       <div class=\"lastMessage\">"+messages.get(messages.size()-1).getMessage()+"</div>\n" +
                                "   </div>" +
                                "</div></a>");
                        }
                    %>


        </div>
        <div class="messages1">
            <%
                ArrayList<Integer> chats = gestion.getAllChats(loggedInUser.getId());
                for (Integer product_id : gestion.getAllChats(loggedInUser.getId())) {

                    ArrayList<Message> messages = gestion.getAllMessages(loggedInUser.getId(), product_id);
                    if(request.getParameter("Product_ID")!=null && Integer.parseInt(request.getParameter("Product_ID"))==product_id){
                        for (Message message : messages) {
                            if(message.getID_User_Reciever()==loggedInUser.getId()){
                                out.print("<p style=\"left:10px;position: absolute;\">"+message.getMessage()+"</p><br>");
                            }else{
                                out.print("<p style=\"right:10px;position: absolute;\">"+message.getMessage()+"</p><br>");
                            }
                        }

                    }else{

                    }
                }

                //out.print("<p>yooo</p>");

            %>

            <form action="/saveMessage" method="post">
                <label for="message-box" type="hidden"></label>
                <textarea
                        maxlength="150"
                        id="message-box" name="message"
                        placeholder="Escribe algo aqui, maximo 150 characteres"
                ></textarea>
                <input type="hidden" name="userId" value="<%=gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString()).getId()%>">
                <input type="hidden" name="productId" value="<%
                if(request.getParameter("Product_ID")!=null){
                    out.print(request.getParameter("Product_ID"));
                }
                %>">
                <input type="hidden" name="idUserReciever" value="<%
                if(request.getParameter("Product_ID")!=null){
                    if(Integer.parseInt(request.getParameter("Product_ID"))==loggedInUser.getId()){
                        ArrayList<Message> messages = gestion.getAllMessages(loggedInUser.getId(), Integer.parseInt(request.getParameter("Product_ID")));

                        out.print(messages.get(messages.size()-1).getID_User_Sender());
                    }else{
                        Producto producto = gestion.getProductoPorID(Integer.parseInt(request.getParameter("Product_ID")));
                        out.print(producto.getIdUsuario());
                    }
                }
                %>">
                <button type="submit" class="send"><i class="far fa-paper-plane"></i></button>
            </form>
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
