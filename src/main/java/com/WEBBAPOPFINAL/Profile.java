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
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);

        if( session != null && session.getAttribute("loggedInUser")!=null) {
            String userEmail = session.getAttribute("loggedInUser").toString();
            GestionAPP gestionAPP = new GestionAPP();
            Usuario user = gestionAPP.getUsuarioPorEmail(userEmail.toString());

            if(request.getAttribute("Product_ID")!=null){
                int idProd = Integer.parseInt(request.getAttribute("Product_ID").toString());
                int chat_id = Integer.parseInt(request.getAttribute("chat_id").toString());

                RequestDispatcher rd = request.getRequestDispatcher("/Profile?Option=messages");
                rd.forward(request, response);


            }
            request.getParameter("newProduct");
            RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
            rd.forward(request, response);

        }else{
            response.sendRedirect("/Login");
        }
    }
}

