package DAO;

import Modelo.Producto;

import java.sql.*;
import java.util.ArrayList;

public class ProductoSQL implements DaoProducto {
    @Override
    public boolean insert(Producto producto, DAOManager dao) {
        String sentencia;
        Connection conn = dao.getConn();
        try {
            Statement st = conn.createStatement();
            sentencia = "INSERT INTO Producto VALUES ('"
                    + producto.getid() + "','"
                    + producto.getNombre() + "','"
                    + producto.getDescripcion() + "','"
                    + producto.getCategoria() + "','"
                    + producto.getPrecio() + "','"
                    + producto.getFecha() + "','"
                    + producto.getEstado() + "','"
                    + producto.getIdUsuario() + "','"
                    + producto.getVendido() + "','"
                    + producto.getDeleted() + "','"
                    + producto.getReserved() + "','"
                    + producto.getImage() +"');";

            st.executeUpdate(sentencia);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Producto producto, DAOManager dao) {

       String sentencia;

        sentencia = "UPDATE Producto SET nombre = '"
                + producto.getNombre() + "', descripcion = '"
                + producto.getDescripcion() + "', categoria = '"
                + producto.getCategoria() + "', precio = '"
                + producto.getPrecio() + "', fecha = '"
                + producto.getFecha() + "', estado = '"
                + producto.getEstado() + "', idUsuario = '"
                + producto.getIdUsuario() + "', vendido = '"
                + producto.getVendido() + "', deleted = '"
                + producto.getDeleted() + "', reserved = '"
                + producto.getReserved() +"', reserved = '"
                + producto.getImage() + "' WHERE id='"+producto.getid()+"';";

        try (Statement stmt = dao.getConn().createStatement()) {
            // enviar el commando insert
            stmt.executeUpdate(sentencia);
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
                            rs.getFloat("precio"),
                            rs.getString("fecha"),
                            rs.getString("estado"),
                            rs.getInt("idUsuario"),
                            rs.getInt("vendido"),
                            rs.getInt("deleted"),
                            rs.getInt("reserved"),
                            rs.getString("image"));
                }
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
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

}
