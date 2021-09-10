package com.WEBBAPOPFINAL;

import Modelo.GestionAPP;
import Modelo.Usuario;

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

//@WebServlet(name = "UpdateUser", value = "/UpdateUser")
public class UpdateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //private info
        String birthday = request.getParameter("date");
        String sex = request.getParameter("sexo");
        String email = request.getParameter("email");
        GestionAPP gestionAPP = new GestionAPP();
        Usuario user = gestionAPP.getUsuarioPorEmail(email);
        gestionAPP.updateUsuario(user);

        request.getSession().setAttribute("loggedInUser", email);
        request.getSession().setAttribute("gestion", gestionAPP);
        RequestDispatcher rd = request.getRequestDispatcher("/Home");
        rd.forward(request, response);
        //public info
        String location = request.getParameter("location");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");


    }
}
