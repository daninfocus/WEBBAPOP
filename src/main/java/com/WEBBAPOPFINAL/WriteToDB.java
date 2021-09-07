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

//@WebServlet(name = "save", value = "/Save")
public class WriteToDB extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("make");
        String model = request.getParameter("model");
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        int id = Integer.parseInt(request.getParameter("userId"));
        String category = request.getParameter("category");
        String sold = request.getParameter("sold");
        String state = request.getParameter("state");

        GestionAPP gestionAPP = new GestionAPP();
        Producto producto = new Producto(0,name,description,category,price,LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),state,id,0,0);
        gestionAPP.addProducto(producto);
        response.sendRedirect("/Profile");


    }
}
