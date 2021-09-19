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
    GestionAPP gestion = (GestionAPP)session.getAttribute("gestion");

    Connection conn = gestion.getConn();

    if ( request.getParameter("imgID") != null && Integer.parseInt(request.getParameter("imgID"))!=0)
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
    }else{
        if(Integer.parseInt(request.getParameter("imgID"))==0){
            conn.close();
        }
    }


%>
