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

//@WebServlet(name = "DeleteProduct", value = "/deleteProduct")
public class DeleteProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GestionAPP gestionAPP = new GestionAPP();
        String userEmail = request.getParameter("userEmail");
        Usuario user = gestionAPP.getUsuarioPorEmail(userEmail);
        ArrayList<Producto> productos = gestionAPP.getProductosDeUsuario(user.getId());
        int totalProd= productos.size();
        int product_id = 0;
        String aux = "checkbox"+1;
        String temp;
        for (int i = 0; i <= totalProd; i++) {
            temp = request.getParameter("checkbox"+i);
            if(temp!=null) {
                product_id = Integer.parseInt(temp);
                gestionAPP.sellProduct(product_id);
            }
        }
        response.sendRedirect("/Profile");
    }
}
