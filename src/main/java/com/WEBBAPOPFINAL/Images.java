package com.WEBBAPOPFINAL;

import java.sql.*;
import java.io.*;
import java.util.*;

public class Images {
    /*-------------------------
     *   Get the Blob image
     *------------------------*/
    public static byte[] getPhoto(Connection conn, int productID)
            throws Exception, SQLException {
        String req = "";
        Blob img = null;
        byte[] imgData = null;
        Statement stmt = conn.createStatement();

        // Query
        req = "select image from Producto where id = '" + productID + "'";


        stmt.execute(req);

        try (ResultSet rs = stmt.getResultSet()) {

            if (rs.next()) {
                img = rs.getBlob(1);
                imgData = img.getBytes(1, (int) img.length());
            }
        }

        stmt.close();

        return imgData;

    }

}