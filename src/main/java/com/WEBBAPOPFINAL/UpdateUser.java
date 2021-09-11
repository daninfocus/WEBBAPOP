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


        String option = request.getParameter("option");

        if (option != null) {
            GestionAPP gestionAPP = new GestionAPP();
            String email = request.getParameter("email");
            Usuario user = gestionAPP.getUsuarioPorEmail(email);


            if (option.equals("firstOption")) {
                //private info
                String birthday = request.getParameter("date");
                String sex = request.getParameter("sexo");

                user.setFecha_nacimiento(birthday);
                user.setSexo(sex);
                gestionAPP.updateUsuario(user);

                request.getSession().setAttribute("loggedInUser", email);
                request.getSession().setAttribute("gestion", gestionAPP);
                request.setAttribute("password",user.getPassword());
                RequestDispatcher rd = request.getRequestDispatcher("/user.jsp");
                rd.forward(request, response);
            }

            if (option.equals("secondOption")) {
                //public info
                String location = request.getParameter("location");
                String name = request.getParameter("username");
                String surname = request.getParameter("surname");

                user.setDireccion(location);
                user.setNombre(name);
                user.setApellidos(surname);
                gestionAPP.updateUsuario(user);

                request.getSession().setAttribute("loggedInUser", email);
                request.getSession().setAttribute("gestion", gestionAPP);
                RequestDispatcher rd = request.getRequestDispatcher("/user.jsp");
                rd.forward(request, response);

            }
        }
    }
}
