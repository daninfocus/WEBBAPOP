package com.WEBBAPOPFINAL;

import Modelo.GestionAPP;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//@WebServlet(name = "Sold", value = "/Sold")
public class Sold extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("Product_ID"));
        GestionAPP gestionAPP = new GestionAPP();
        gestionAPP.sellProduct(productID);
        response.sendRedirect("/Profile");
    }
}
