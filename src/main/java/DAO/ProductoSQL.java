package DAO;

import Modelo.Producto;

import javax.servlet.http.Part;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class ProductoSQL implements DaoProducto {
    @Override
    public boolean insert(Producto producto,InputStream blob, DAOManager dao) {
        int result = 0;
        try {
            Connection con = dao.getConn();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Producto values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            //InputStream is = part.getInputStream();
            ps.setInt(1, 0);
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setString(4, producto.getCategoria());
            ps.setString(5, producto.getExtraInfo());
            ps.setFloat(6, producto.getPrecio());
            ps.setString(7, producto.getFecha());
            ps.setString(8, producto.getEstado());
            ps.setInt(9, producto.getIdUsuario());
            ps.setInt(10, 0);
            ps.setInt(11, 0);
            ps.setInt(12, 0);
            ps.setBlob(13,blob);
            result = ps.executeUpdate();

            ps.close();
            if(result>0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(Producto producto, DAOManager dao) {

       String sentencia;

        sentencia = "UPDATE Producto SET nombre = '"
                + producto.getNombre() + "', descripcion = '"
                + producto.getDescripcion() + "', categoria = '"
                + producto.getCategoria() + "', extraInfo = '"
                + producto.getExtraInfo() + "', precio = '"
                + producto.getPrecio() + "', fecha = '"
                + producto.getFecha() + "', estado = '"
                + producto.getEstado() + "', idUsuario = '"
                + producto.getIdUsuario() + "', vendido = '"
                + producto.getVendido() + "', deleted = '"
                + producto.getDeleted() + "', reserved = '"
                + producto.getReserved() + "' WHERE id='"+producto.getid()+"';";

        try (Statement stmt = dao.getConn().createStatement()) {
            // enviar el commando insert
            stmt.executeUpdate(sentencia);
            stmt.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id, DAOManager dao) {
        String sentencia;
        sentencia = "UPDATE Producto set deleted='1' where id = '" + id + "';";

        try (Statement stmt = dao.getConn().createStatement()) {
            // enviar el commando delete
            stmt.executeUpdate(sentencia);
            stmt.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public Producto read(int id, DAOManager dao) {
        Producto producto = null;
        String sentencia;
        sentencia = "select * from Producto where id = ?";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setString(1, String.valueOf(id));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    producto = new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getString("categoria"),
                            rs.getString("extraInfo"),
                            rs.getFloat("precio"),
                            rs.getString("fecha"),
                            rs.getString("estado"),
                            rs.getInt("idUsuario"),
                            rs.getInt("vendido"),
                            rs.getInt("deleted"),
                            rs.getInt("reserved"),
                            rs.getString("image"));
                }
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return producto;
    }

    @Override
    public ArrayList<Producto> getAll(DAOManager dao) {
        Producto producto = null;
        ArrayList<Producto> productos = new ArrayList<>();
        String sentencia;
        sentencia = "select * from Producto order by precio";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    producto = new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getString("categoria"),
                            rs.getString("extraInfo"),
                            rs.getFloat("precio"),
                            rs.getString("fecha"),
                            rs.getString("estado"),
                            rs.getInt("idUsuario"),
                            rs.getInt("vendido"),
                            rs.getInt("deleted"),
                            rs.getInt("reserved"),
                            rs.getString("image"));
                    productos.add(producto);
                }
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productos;
    }

    @Override
    public ArrayList<Producto> getAllFromUser(int id, DAOManager dao) {
        Producto producto = null;
        ArrayList<Producto> productos = new ArrayList<>();
        String sentencia;
        sentencia = "select * from Producto where idUsuario = ?";

        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setString(1, String.valueOf(id));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    producto = new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getString("categoria"),
                            rs.getString("extraInfo"),
                            rs.getFloat("precio"),
                            rs.getString("fecha"),
                            rs.getString("estado"),
                            rs.getInt("idUsuario"),
                            rs.getInt("vendido"),
                            rs.getInt("deleted"),
                            rs.getInt("reserved"),
                            rs.getString("image"));
                    productos.add(producto);
                }
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productos;
    }

    @Override
    public boolean sell(int productID, DAOManager dao) {
        String sentencia;
        sentencia = "UPDATE Producto set vendido='1' where id = '" + productID + "';";

        try (Statement stmt = dao.getConn().createStatement()) {
            // enviar el commando delete
            stmt.executeUpdate(sentencia);
            stmt.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public ArrayList<Producto> searchByText(String text, DAOManager dao) {
        Producto producto = null;
        ArrayList<Producto> productos = new ArrayList<>();
        String sentencia;
        sentencia = "select * from Producto where nombre LIKE ? or descripcion like ? or categoria like ? or extraInfo like ? COLLATE utf8mb4_0900_ai_ci";

        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setString(1, "%"+text+"%");
            ps.setString(2, "%"+text+"%");
            ps.setString(3, "%"+text+"%");
            ps.setString(4, "%"+text+"%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    producto = new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getString("categoria"),
                            rs.getString("extraInfo"),
                            rs.getFloat("precio"),
                            rs.getString("fecha"),
                            rs.getString("estado"),
                            rs.getInt("idUsuario"),
                            rs.getInt("vendido"),
                            rs.getInt("deleted"),
                            rs.getInt("reserved"),
                            rs.getString("image"));
                    productos.add(producto);
                }
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productos;
    }

}
