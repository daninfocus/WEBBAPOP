package com.WEBBAPOPFINAL;

import Modelo.Chat;
import Modelo.GestionAPP;
import Modelo.Producto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class OpenChat extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ID_User = Integer.parseInt(request.getParameter("ID_User"));
        int ID_Prod = Integer.parseInt(request.getParameter("Product_ID"));
        GestionAPP gestionAPP = new GestionAPP();
        ArrayList<Chat> chats = gestionAPP.getUserChatsWithProdId(ID_User,ID_Prod);
        Producto producto = gestionAPP.getProductoPorID(ID_Prod);
        if(gestionAPP.doesChatExist(ID_User, producto.getIdUsuario(),ID_Prod)==-1){
            if ((chats != null && chats.size() == 0) || chats == null) {
                request.setAttribute("chat_id",gestionAPP.addChat(ID_User, producto.getIdUsuario(),ID_Prod));
            }
        }else{
            request.setAttribute("chat_id",gestionAPP.doesChatExist(ID_User, producto.getIdUsuario(),ID_Prod));
        }
        request.setAttribute("Product_ID",ID_Prod);
        RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
        rd.forward(request, response);
    }
}
