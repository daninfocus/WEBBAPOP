package com.WEBBAPOPFINAL;

import Modelo.GestionAPP;
import Modelo.Producto;
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
import java.io.IOException;
import java.util.ArrayList;

//@WebServlet(name = "Search", value = "/Search")
public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        GestionAPP gestionAPP = new GestionAPP();
        ArrayList<Producto> productos = gestionAPP.buscaProductoTexto(search);
        request.setAttribute("productos",productos);
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
