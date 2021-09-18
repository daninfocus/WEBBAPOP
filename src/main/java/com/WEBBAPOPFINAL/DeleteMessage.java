package com.WEBBAPOPFINAL;

import Modelo.GestionAPP;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
public class DeleteMessage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        GestionAPP gestionAPP = new GestionAPP();

        gestionAPP.deleteMessage((Integer.parseInt(request.getParameter("ID").toString())));
        RequestDispatcher rd = request.getRequestDispatcher("/Profile?Option=messages");
        rd.include(request, response);
    }
}
