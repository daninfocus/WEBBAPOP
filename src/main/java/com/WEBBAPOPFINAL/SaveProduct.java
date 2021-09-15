package com.WEBBAPOPFINAL;

import Modelo.GestionAPP;
import Modelo.SavedProducts;

import javax.jws.soap.SOAPBinding;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

//@WebServlet(name = "SaveProduct", value = "/SaveProduct")
public class SaveProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String delete = request.getParameter("Delete");
        String UserId = request.getParameter("User_ID");
        GestionAPP gestion = (GestionAPP) request.getSession().getAttribute("gestion");
        if (delete != null) {
            int prodid = Integer.parseInt(delete);
            for (SavedProducts savedProducts : gestion.getSavedProducts(Integer.parseInt(UserId))) {
                if (savedProducts.getProduct_ID() == prodid && savedProducts.getUser_ID() == Integer.parseInt(UserId)) {
                    gestion.deleteSavedProduct(savedProducts);
                }
            }


            RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
            rd.forward(request, response);
        } else {
            String ProdId = request.getParameter("Product_ID");


            if (ProdId != null && UserId != null) {

                ArrayList<SavedProducts> productstest = gestion.getSavedProducts(Integer.parseInt(UserId));
                boolean exists = false;
                for (SavedProducts savedProducts : productstest) {
                    if (savedProducts.getProduct_ID() == Integer.parseInt(ProdId) && savedProducts.getUser_ID() == Integer.parseInt(UserId)) {
                        exists = true;
                    }
                }
                if (!exists) {
                    SavedProducts products = new SavedProducts(Integer.parseInt(ProdId), Integer.parseInt(UserId));
                    gestion.insertSavedProduct(products);
                }

            }
            RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
