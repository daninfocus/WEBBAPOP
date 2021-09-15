package com.WEBBAPOPFINAL;

import Modelo.GestionAPP;
import Modelo.Message;
import Modelo.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class SaveMessage extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String messageContent = request.getParameter("message");
        int idSender = Integer.parseInt(request.getParameter("userId"));
        int idProduct = Integer.parseInt(request.getParameter("productId"));
        int idUserReciever = Integer.parseInt(request.getParameter("idUserReciever"));


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Calendar cal = new GregorianCalendar();

        GestionAPP gestionAPP = new GestionAPP();
        Message message = new Message(0,idSender, idUserReciever, idProduct, sdf.format(cal.getTime()), "", messageContent, 0, 0);
        gestionAPP.saveMessage(message);
        response.sendRedirect("/Profile?Product_ID="+idProduct);


    }
}
