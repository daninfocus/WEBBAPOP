package com.WEBBAPOPFINAL;

import Modelo.GestionAPP;
import Modelo.Producto;
import Modelo.Usuario;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReserveProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        int productID = Integer.parseInt(request.getParameter("Product_ID"));
        GestionAPP gestionAPP = new GestionAPP();
        Producto producto = gestionAPP.getProductoPorID(productID);
        if(gestionAPP.isProductReserved(producto)) {
            producto.setReserved(0);
            gestionAPP.updateProducto(producto);
        }else gestionAPP.reserveProduct(productID);

        response.sendRedirect("/Profile");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}