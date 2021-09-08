package com.WEBBAPOPFINAL;

import Modelo.GestionAPP;
import Modelo.Producto;
import Modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ReserveProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("Product_ID"));
        GestionAPP gestionAPP = new GestionAPP();
        gestionAPP.reserveProduct(productID);
        response.sendRedirect("/Profile");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}