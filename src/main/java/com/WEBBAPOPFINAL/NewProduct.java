package com.WEBBAPOPFINAL;

import DAO.DAOManager;
import Modelo.GestionAPP;
import Modelo.Producto;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.security.core.parameters.P;

//@WebServlet(name = "save", value = "/Save")\
@MultipartConfig(maxFileSize = 16177216)//1.5mb
public class NewProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //String name = request.getParameter("make");
        //String model = request.getParameter("model");
        //String description = request.getParameter("description");
        //String price = request.getParameter("price");
        //float priceNum = Float.parseFloat(price);
        //int id = Integer.parseInt(request.getParameter("userId"));
        //String category = request.getParameter("category");
        //String sold = request.getParameter("sold");
        //String state = request.getParameter("state");
        response.setContentType("text/html;charset=UTF-8");

        GestionAPP gestionAPP = new GestionAPP();
        Producto producto = new Producto(0,"","","",0f,"","",0,0,0,0,"");

        if(!ServletFileUpload.isMultipartContent(request)){
            throw new ServletException("Content type is not multipart/form-data");
        }

        try {

            FileItemFactory factory = new DiskFileItemFactory();

            ServletFileUpload upload = new ServletFileUpload( factory );
            upload.setSizeMax(5242880);

            List<FileItem> uploadItems = upload.parseRequest( request );
            String fileBlob="";
            InputStream fileContent=null;
            for( FileItem uploadItem : uploadItems )
            {

                if( uploadItem.isFormField() )
                {
                    String fieldName = uploadItem.getFieldName();
                    String value = uploadItem.getString();
                    if(fieldName.equals("make")) producto.setNombre(value);
                    if(fieldName.equals("description")) producto.setDescripcion(value);
                    if(fieldName.equals("category")) producto.setCategoria(value);
                    if(fieldName.equals("price")) producto.setPrecio(Float.parseFloat(value));
                    if(fieldName.equals("state")) producto.setEstado(value);
                    if(fieldName.equals("userId")) producto.setIdUsuario(Integer.parseInt(value));
                }else{

                    fileBlob = uploadItem.getString();
                    String fieldName = uploadItem.getFieldName();
                    String fileName = FilenameUtils.getName(uploadItem.getName());
                    fileContent = uploadItem.getInputStream();
                }
            }

            producto.setFecha();

            int result = 0;
            try {
                Connection con = gestionAPP.getConn();
                PreparedStatement ps = con.prepareStatement("INSERT INTO Producto values(?,?,?,?,?,?,?,?,?,?,?,?)");
                //InputStream is = part.getInputStream();
                ps.setInt(1,0);
                ps.setString(2,producto.getNombre());
                ps.setString(3,producto.getDescripcion());
                ps.setString(4,producto.getCategoria());
                ps.setFloat(5,producto.getPrecio());
                ps.setString(6,producto.getFecha());
                ps.setString(7,producto.getEstado());
                ps.setInt(8,producto.getIdUsuario());
                ps.setInt(9,0);
                ps.setInt(10,0);
                ps.setInt(11,0);
                ps.setBlob(12, fileContent);
                result = ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }

            response.sendRedirect("/Profile");
        } catch (FileUploadException e) {
            System.out.println("Exception in uploading file.");
        } catch (Exception e) {
            System.out.println("Exception in uploading file.");
        }
    }
}
