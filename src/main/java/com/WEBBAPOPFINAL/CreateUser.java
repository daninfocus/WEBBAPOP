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
public class CreateUser extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String pass2 = request.getParameter("password2");

        if(pass.equals(pass2)){
            GestionAPP gestion = new GestionAPP();
            if (gestion.getUsuarioPorEmail(email)==null){

                Usuario newUsuario = new Usuario(0,name,surname,"","","",email,"",pass,0);
                gestion.addUsuario(newUsuario);

                request.getSession().setAttribute("loggedInUser", email);
                request.getSession().setAttribute("gestion", gestion);

                RequestDispatcher rd = request.getRequestDispatcher("/Home");
                rd.forward(request, response);
            }else{
                request.setAttribute("error", "Email ya existe");
                RequestDispatcher rd = request.getRequestDispatcher("/Signup");
                rd.include(request, response);
            }
        }else{
            request.setAttribute("error", "Contraseñas no coinciden");
            RequestDispatcher rd = request.getRequestDispatcher("/Signup");
            rd.include(request, response);
        }
    }
}
