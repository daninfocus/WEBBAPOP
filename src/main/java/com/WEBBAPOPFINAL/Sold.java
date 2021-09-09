package com.WEBBAPOPFINAL;

import Modelo.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//@WebServlet(name = "Sold", value = "/Sold")
public class Sold extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int productID = Integer.parseInt(request.getParameter("Product_ID"));


        HttpSession session = request.getSession(false);

        GestionAPP gestion = (GestionAPP) session.getAttribute("gestion");
        Usuario loggedInUser = gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString());
        int product_id = Integer.parseInt(request.getParameter("Product_ID"));

        int idLoggedInUser = loggedInUser.getId();

        ArrayList<Message> messages = gestion.getAllMessages(idLoggedInUser, product_id);
        if (messages.size() == 0) {
            request.setAttribute("OpenChats", "No");
            RequestDispatcher rd = request.getRequestDispatcher("/SellMenu");
            rd.forward(request, response);
        } else {
            request.setAttribute("OpenChats", "Yes");
            RequestDispatcher rd = request.getRequestDispatcher("/SellMenu");
            rd.forward(request, response);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("Product_ID"));

        if (request.getParameter("FormComplete") != null || request.getParameter("Outside") != null) {
            HttpSession session = request.getSession(false);
            GestionAPP gestion = (GestionAPP) session.getAttribute("gestion");

            if (request.getParameter("FormComplete") != null) {
                Usuario usuarioVendedor = gestion.getUsuarioPorEmail(session.getAttribute("loggedInUser").toString());
                String buyerEmail = request.getParameter("buyerEmail");
                Usuario usuarioComprador = gestion.getUsuarioPorEmail(buyerEmail);

                int price = Integer.parseInt(request.getParameter("price"));
                int points = Integer.parseInt(request.getParameter("points"));
                String review = request.getParameter("reviewContent");
                Trato tratoCompra = new Trato(0, "Compra", usuarioComprador.getId(), usuarioVendedor.getEmail(),productID,"",price,"",0,0 );
                Trato tratoVenta = new Trato(0, "Venta", usuarioVendedor.getId(), buyerEmail,productID,LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(),price,review,points,0 );

                gestion.addTrato(tratoCompra);
                gestion.addTrato(tratoVenta);
                gestion.sellProduct(productID);

                String sentencia;
                Connection conn = gestion.getConn();
                try {
                    Statement st = conn.createStatement();
                    sentencia = "INSERT INTO Trato(id,tipoTrato,idUsuario,emailOtroUser,idProducto,fecha,precio,comentario,puntuacion,completado) VALUES ('"
                            + tratoCompra.getId() + "','"
                            + tratoCompra.getTipoTrato() + "','"
                            + tratoCompra.getIdUsuario() + "','"
                            + tratoCompra.getEmailOtroUsuario() + "','"
                            + tratoCompra.getIdProducto() + "','"
                            + tratoCompra.getFecha() + "','"
                            + tratoCompra.getPrecio() + "','"
                            + tratoCompra.getComentario() + "','"
                            + tratoCompra.getPuntuacion() + "','"
                            + tratoCompra.getCompletado() + "');";

                    st.executeUpdate(sentencia);
                } catch (Exception e) {
                    System.out.println("no");
                }
                System.out.println("yes");


                response.sendRedirect("/Profile");

            }

            if (request.getParameter("Outside") != null) {
                gestion.sellProduct(productID);
            }
        }
    }
}
