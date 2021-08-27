package com.WEBBAPOPFINAL;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class UserDAO {
    static final String URL = "jdbc:mysql://127.0.0.1:3030/fernanpop"; //Enlazo la dirección del servidor y de la base de datos a usar
    static final String USER = "admin"; //Usuario de la BBDD
    static final String PASS = "admin"; //Clave de la BBDD


    public static boolean validate(String email, String pass, HttpServletRequest request){
        boolean status=false;

        PreparedStatement ps= null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            ps = conn.prepareStatement("select * from Usuario where email=? and password=?");

            ps.setString(1,email);
            ps.setString(2,pass);

            ResultSet rs=ps.executeQuery();

            status = rs.next();

            String name = rs.getString(2);
            request.getSession().setAttribute("name",name);

            conn.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return status;
    }

}