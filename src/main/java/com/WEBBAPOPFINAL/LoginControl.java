package com.WEBBAPOPFINAL;

import Modelo.GestionAPP;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Home")
public class LoginControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        if(email!=null && pass!=null){
            GestionAPP gestion = new GestionAPP();

            if (gestion.login(email, pass)) {

                request.getSession().setAttribute("loggedInUser", email);
                request.getSession().setAttribute("gestion", gestion);
                request.getSession().setAttribute("name", gestion.getUsuarioPorEmail(email).getNombre());

                RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
                rd.forward(request, response);

            } else {
                request.setAttribute("error", "Contraseña o usuario incorrecto");
                RequestDispatcher rd = request.getRequestDispatcher("/Login");
                rd.include(request, response);
            }
        }else{
            response.sendRedirect("/Login");
        }


        out.close();
    }
}