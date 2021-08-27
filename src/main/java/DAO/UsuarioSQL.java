package DAO;

import Modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioSQL implements DaoUsuario {

    @Override
    public boolean insert(Usuario usuario, DAOManager dao) {
        String sentencia;
        Connection conn = dao.getConn();
        try {
            Statement st = conn.createStatement();
            sentencia = "INSERT INTO Usuario VALUES ('"
                    + usuario.getId() + "','"
                    + usuario.getNombre() + "','"
                    + usuario.getApellidos() + "','"
                    + usuario.getDireccion() + "','"
                    + usuario.getTelefono() + "','"
                    + usuario.getSexo() + "','"
                    + usuario.getEmail() + "','"
                    + usuario.getFecha_nacimiento() + "','"
                    + usuario.getPassword()  + "','"
                    + usuario.getValoracionesPendientes()+ "','"
                    + usuario.getNotaMedia() +  "');";

            st.executeUpdate(sentencia);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Usuario usuario, DAOManager dao) {
        String sentencia;

        sentencia = "UPDATE Usuario SET nombre = '"
                + usuario.getNombre() + "', apellidos = '"
                + usuario.getApellidos() + "', direccion = '"
                + usuario.getDireccion() + "', telefono = '"
                + usuario.getTelefono() + "', sexo = '"
                + usuario.getSexo() + "', email = '"
                + usuario.getEmail() + "', fecha_nacimiento = '"
                + usuario.getFecha_nacimiento() + "', password = '"
                + usuario.getPassword() + "', nota_media = '"
                + usuario.getNotaMedia() +"' WHERE id='"+usuario.getId()+"';";

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
    public Usuario read(String email, DAOManager dao) {
        Usuario usuario = null;
        String sentencia;
        sentencia = "select * from Usuario where email = ?";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    usuario = new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("sexo"),
                            rs.getString("email"),
                            rs.getString("fecha_nacimiento"),
                            rs.getString("password"),
                            rs.getDouble("nota_media"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuario;
    }

    @Override
    public Usuario readID(int id, DAOManager dao) {
        Usuario usuario = null;
        String sentencia;
        sentencia = "select * from Usuario where id = ?";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    usuario = new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("sexo"),
                            rs.getString("email"),
                            rs.getString("fecha_nacimiento"),
                            rs.getString("password"),
                            rs.getDouble("nota_media"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuario;
    }

    @Override
    public boolean delete(int id, DAOManager dao) {
        String sentencia;
        sentencia = "delete from Usuario where id = '" + id + "';";

        try (Statement stmt = dao.getConn().createStatement()) {
            // enviar el commando delete
            stmt.executeUpdate(sentencia);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public ArrayList<Usuario> getAll(DAOManager dao) {
        Usuario usuario = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sentencia = "select * from Usuario";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usuario = new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("sexo"),
                            rs.getString("email"),
                            rs.getString("fecha_nacimiento"),
                            rs.getString("password"),
                            rs.getDouble("nota_media"));
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }
}
