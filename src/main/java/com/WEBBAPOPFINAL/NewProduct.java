package com.WEBBAPOPFINAL;

import Modelo.GestionAPP;
import Modelo.Producto;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.Notificaciones;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

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

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        GestionAPP gestionAPP = new GestionAPP();
        Producto producto = new Producto(0, "", "", "", "", 0f, "", "", 0, 0, 0, 0, "");


        if (!ServletFileUpload.isMultipartContent(request)) {
            System.out.println("Content type is not multipart/form-data");
            String option = request.getParameter("Option");
            if (option != null && option.equals("Edit")) {

                int product_id = Integer.parseInt(request.getParameter("Product_ID"));
                Producto producto1 = gestionAPP.getProductoPorID(product_id);
                String name = request.getParameter("name");
                String desc = request.getParameter("description");
                float price = Float.parseFloat(request.getParameter("price"));
                String state = request.getParameter("state");
                String extraInfo = request.getParameter("extraInfo");
                producto1.setNombre(name);
                producto1.setPrecio(price);
                producto1.setEstado(state);
                producto1.setDescripcion(desc);
                producto1.setPrecio(price);
                producto1.setExtraInfo(extraInfo);

                gestionAPP.updateProducto(producto1);
                response.sendRedirect("/Profile");

            } else {

            }
        } else {


            try {

                FileItemFactory factory = new DiskFileItemFactory();

                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(5242880);

                List<FileItem> uploadItems = upload.parseRequest(request);
                String fileBlob = "";
                InputStream fileContent = null;
                for (FileItem uploadItem : uploadItems) {

                    if (uploadItem.isFormField()) {
                        String fieldName = uploadItem.getFieldName();
                        String value = uploadItem.getString();
                        if (fieldName.equals("name")) producto.setNombre(value);
                        if (fieldName.equals("description")) producto.setDescripcion(value);
                        if (fieldName.equals("category")) producto.setCategoria(value);
                        if (fieldName.equals("price")) producto.setPrecio(Float.parseFloat(value));
                        if (fieldName.equals("state")) producto.setEstado(value);
                        if (fieldName.equals("userId")) producto.setIdUsuario(Integer.parseInt(value));
                    } else {

                        fileBlob = uploadItem.getString();
                        String fieldName = uploadItem.getFieldName();
                        String fileName = FilenameUtils.getName(uploadItem.getName());
                        fileContent = uploadItem.getInputStream();
                    }
                }

                producto.setFecha();

                if (gestionAPP.addProducto(producto, fileContent)) {
                    Notificaciones.enviaMailProductoRegistrado(gestionAPP.getUsuarioPorId(producto.getIdUsuario()), producto);
                    response.sendRedirect("/Profile");
                }


            } catch (FileUploadException e) {
                System.out.println("Exception in uploading file.");
            } catch (Exception e) {
                System.out.println("Exception in uploading file.");
            }
        }
    }
}

