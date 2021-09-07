package com.WEBBAPOPFINAL;

import DAO.DAOManager;
import Modelo.GestionAPP;
import Modelo.Message;
import Modelo.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.MidiUnavailableException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

//@WebServlet("/Profile")
public class Profile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if( session != null || session.getAttribute("loggedInUser")!=null) {
            String userEmail = session.getAttribute("loggedInUser").toString();
            GestionAPP gestionAPP = new GestionAPP();
            Usuario user = gestionAPP.getUsuarioPorEmail(userEmail.toString());

            if(request.getParameter("Product_ID")!=null){
                int idProd = Integer.parseInt(request.getParameter("Product_ID"));
                ArrayList<Message> messages = gestionAPP.getAllMessages(user.getId(), idProd);
                if(messages.size()==0){
                    Message message = new Message(0,user.getId(),gestionAPP.getProductoPorID(idProd).getid(),idProd,"00/00/0000","00/00/0000","",0,0);
                    gestionAPP.saveMessage(message);
                }
            }
            request.getParameter("newProduct");
            RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
            rd.forward(request, response);

        }else{
            response.sendRedirect("/Login");
        }
    }
}

