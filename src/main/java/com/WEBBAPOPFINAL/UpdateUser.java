package com.WEBBAPOPFINAL;

import Modelo.GestionAPP;
import Modelo.Usuario;
import Utils.Notificaciones;

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
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String option = request.getParameter("option");

        GestionAPP gestionAPP = new GestionAPP();


        if (option != null) {

            String email = request.getParameter("email");
            Usuario user = gestionAPP.getUsuarioPorEmail(email);


            if (option.equals("firstOption")) {
                //private info
                String birthday = request.getParameter("date");
                String sex = request.getParameter("sexo");
                String telephone = request.getParameter("telephone");
                String password = request.getParameter("password");

                if(!password.equals("secret")){
                    if(!user.getPassword().equals(password)){
                        int token = (int) ((Math.random() * 8999) + 1000);

                        request.getSession().setAttribute("token",token);
                        Notificaciones notificaciones = new Notificaciones();

                        Notificaciones.enviaTokenRegistro(gestionAPP.getUsuarioPorEmail(request.getSession().getAttribute("loggedInUser").toString()).getEmail(), token);
                        request.setAttribute("password",password);
                        request.setAttribute("Change",user);
                        RequestDispatcher rd = request.getRequestDispatcher("/signup_code.jsp");
                        rd.forward(request, response);
                    }
                }

                user.setFecha_nacimiento(birthday);
                user.setSexo(sex);
                user.setTelefono(telephone);
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
