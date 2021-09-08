package com.WEBBAPOPFINAL;

import Modelo.GestionAPP;
import Modelo.Producto;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//@WebServlet(name = "Sold", value = "/Sold")
public class Sold extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("Product_ID"));
        GestionAPP gestionAPP = new GestionAPP();
        gestionAPP.sellProduct(productID);
        response.sendRedirect("/Profile");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
