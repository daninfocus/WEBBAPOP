<%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 08/09/2021
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java.io.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="Modelo.GestionAPP" %>
<jsp:useBean id="photo" class="com.WEBBAPOPFINAL.Images" scope="session" />
<%

    int productID ;
    GestionAPP gestionAPP = new GestionAPP();
    Connection conn = gestionAPP.getConn();

    if ( request.getParameter("imgID") != null )
    {

        productID = Integer.parseInt(request.getParameter("imgID")) ;

        try
        {
            conn.setAutoCommit (false);

            // get the image from the database
            byte[] imgData = photo.getPhoto( conn, productID ) ;
            // display the image
            response.setContentType("image/jpeg");
            OutputStream o = response.getOutputStream();
            o.write(imgData);
            o.flush();
            o.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        finally
        {
            conn.close();
        }
    }


%>
