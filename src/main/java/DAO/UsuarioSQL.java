package DAO;

import Modelo.Producto;
import Modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioSQL implements DaoUsuario {

    @Override
    public boolean insert(Usuario usuario, DAOManager dao) {
        try {
            PreparedStatement ps = dao.getConn().prepareStatement("INSERT INTO Usuario VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                    ps.setInt(1,usuario.getId());
                    ps.setString(2, usuario.getNombre());
                    ps.setString(3, usuario.getApellidos());
                    ps.setString(4, usuario.getDireccion());
                    ps.setString(5, usuario.getTelefono());
                    ps.setString(6, usuario.getSexo());
                    ps.setString(7, usuario.getEmail());
                    ps.setString(8, usuario.getFecha_nacimiento());
                    ps.setString(9, usuario.getPassword());
                    ps.setInt(10,usuario.getValoracionesPendientes());
                    ps.setDouble(11, usuario.getNotaMedia());

            // enviar el commando insert
            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Usuario usuario, DAOManager dao) {

        try {
            PreparedStatement ps = dao.getConn().prepareStatement("UPDATE Usuario SET nombre = ?,apellidos= ?,direccion= ?,telefono= ?,sexo= ?,email= ?,fecha_nacimiento = ?,password = ?,valoracionesPendientes = ?,nota_media = ? WHERE id = ?");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getDireccion());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getSexo());
            ps.setString(6, usuario.getEmail());
            ps.setString(7, usuario.getFecha_nacimiento());
            ps.setString(8, usuario.getPassword());
            ps.setInt(9, 0);
            ps.setInt(10, 0);
            ps.setInt(11, usuario.getId());

            // enviar el commando insert
            ps.executeUpdate();

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
        sentencia = "select * from Usuario where email ='"+email+"'";

        try {
            Connection conn = dao.getConn();
            if(conn==null){
                dao.open();
                conn = dao.getConn();
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sentencia);

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
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
            }finally{
                ps.close();
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
            stmt.close();
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

            }finally{
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public ArrayList<Usuario> searchByText(String text, DAOManager dao) {
        Usuario usuario = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sentencia;
        sentencia = "select * from Usuario where nombre LIKE ? COLLATE utf8mb4_spanish2_ci";

        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setString(1, "%" + text + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
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
                    usuarios.add(usuario);
                }

            }finally{
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }
}
