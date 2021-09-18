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

public class CreateUser extends HttpServlet {
    private int token;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String pass2 = request.getParameter("password2");

        if(pass!=null && pass2!=null && Usuario.validarPassword(pass) && Usuario.validarPassword(pass2)) {
            if (pass.equals(pass2)) {
                if(Usuario.validarEmail(email)) {

                    GestionAPP gestion = new GestionAPP();
                    if (gestion.getUsuarioPorEmail(email) == null) {

                        if (request.getParameter("code") == null) {
                            token = (int) ((Math.random() * 8999) + 1000);

                            request.getSession().setAttribute("token", token);
                            Notificaciones notificaciones = new Notificaciones();

                            Notificaciones.enviaTokenRegistro(email, token);
                            RequestDispatcher rd = request.getRequestDispatcher("/signup_code.jsp");
                            rd.forward(request, response);
                        } else {
                            int code = Integer.parseInt(request.getParameter("code"));

                            if (code == Integer.parseInt(request.getSession().getAttribute("token").toString())) {

                                Usuario newUsuario = new Usuario(0, name, surname, "", "", "", email, "", pass, 0);
                                gestion.addUsuario(newUsuario);

                                request.getSession().setAttribute("loggedInUser", email);
                                request.getSession().setAttribute("gestion", gestion);
                                RequestDispatcher rd = request.getRequestDispatcher("/Home");
                                rd.forward(request, response);
                            } else {
                                request.setAttribute("error", "Codigo no corresponde");
                                RequestDispatcher rd = request.getRequestDispatcher("/signup_code.jsp");
                                rd.include(request, response);
                            }
                        }


                    } else {
                        request.setAttribute("error", "Email ya existe");
                        RequestDispatcher rd = request.getRequestDispatcher("/Signup");
                        rd.include(request, response);
                    }
                }else{
                    request.setAttribute("error", "Email no es valido");
                    RequestDispatcher rd = request.getRequestDispatcher("/Signup");
                    rd.include(request, response);
                }
            } else {
                request.setAttribute("error", "Contraseñas no coinciden");
                RequestDispatcher rd = request.getRequestDispatcher("/Signup");
                rd.include(request, response);
            }
        }else{
            request.setAttribute("error", "Contraseñas no siguen el patron minimo de seguridad");
            RequestDispatcher rd = request.getRequestDispatcher("/Signup");
            rd.include(request, response);
        }
    }
}
