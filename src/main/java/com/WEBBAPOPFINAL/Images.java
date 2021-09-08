package com.WEBBAPOPFINAL;

import java.sql.*;
import java.io.*;
import java.util.*;

public class Images
{
    /*-------------------------
     *   Get the Blob image
     *------------------------*/
    public static byte[] getPhoto (Connection conn, int productID)
            throws Exception, SQLException
    {
        String req = "" ;
        Blob img ;
        byte[] imgData = null ;
        Statement stmt = conn.createStatement ();

        // Query
        req = "select image from Producto where id = '"+ productID+"'";

        ResultSet rset  = stmt.executeQuery ( req );

        while (rset.next ())
        {
            img = rset.getBlob(1);
            imgData = img.getBytes(1,(int)img.length());
        }

        rset.close();
        stmt.close();

        return imgData ;

    }

}