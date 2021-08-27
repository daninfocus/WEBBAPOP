package DAO;

import Modelo.Trato;

import java.sql.*;
import java.util.ArrayList;

public class TratoSQL implements DaoTrato {
    @Override
    public boolean insert(Trato trato, DAOManager dao) {
        String sentencia;
        Connection conn = dao.getConn();
        try {
            Statement st = conn.createStatement();
            sentencia = "INSERT INTO Trato VALUES ('"
                    + trato.getId() + "','"
                    + trato.getTipoTrato() + "','"
                    + trato.getIdUsuario() + "','"
                    + trato.getEmailOtroUsuario() + "','"
                    + trato.getIdOtroTrato() + "','"
                    + trato.getIdProducto() + "','"
                    + trato.getFecha() + "','"
                    + trato.getPrecio() + "','"
                    + trato.getComentario() + "','"
                    + trato.getPuntuacion() + "','"
                    + trato.getCompletado() + "');";

            st.executeUpdate(sentencia);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Trato trato, DAOManager dao) {

        String sentencia;

        sentencia = "UPDATE Trato SET comentario = '"
                + trato.getComentario() + "', puntuacion = '"
                + trato.getPuntuacion() + "', completado = '"
                + trato.getCompletado() + "' WHERE id='"+trato.getId()+"';";

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
    public boolean delete(Trato trato, DAOManager dao) {
        return false;
    }

    @Override
    public Trato read(int id, DAOManager dao) {
        Trato trato = null;
        String sentencia;

        sentencia = "select * from Trato where id=?";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setString(1, String.valueOf(id));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    trato = new Trato(
                            rs.getInt("id"),
                            rs.getString("tipoTrato"),
                            rs.getInt("idUsuario"),
                            rs.getString("emailOtroUsuario"),
                            rs.getInt("idOtroTrato"),
                            rs.getInt("idProducto"),
                            rs.getString("fecha"),
                            rs.getFloat("precio"),
                            rs.getString("comentario"),
                            rs.getInt("puntuacion"),
                            rs.getInt("completado"));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return trato;
    }

    @Override
    public ArrayList<Trato> readPendiente(int idUsuario, DAOManager dao) {
        Trato trato = null;
        String sentencia;
        ArrayList<Trato> tratos = new ArrayList<>();
        sentencia = "select * from Trato where idUsuario = ? and tipoTrato like 'Compra'";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setString(1, String.valueOf(idUsuario));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    trato = new Trato(
                            rs.getInt("id"),
                            rs.getString("tipoTrato"),
                            rs.getInt("idUsuario"),
                            rs.getString("emailOtroUsuario"),
                            rs.getInt("idOtroTrato"),
                            rs.getInt("idProducto"),
                            rs.getString("fecha"),
                            rs.getFloat("precio"),
                            rs.getString("comentario"),
                            rs.getInt("puntuacion"),
                            rs.getInt("completado"));
                    tratos.add(trato);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tratos;
    }

    @Override
    public ArrayList<Trato> getAll(int idUsuario, DAOManager dao) {
        Trato trato = null;
        String sentencia;
        ArrayList<Trato> tratos = new ArrayList<>();
        sentencia = "select * from Trato where idUsuario = ?";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setString(1, String.valueOf(idUsuario));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    trato = new Trato(
                            rs.getInt("id"),
                            rs.getString("tipoTrato"),
                            rs.getInt("idUsuario"),
                            rs.getString("emailOtroUsuario"),
                            rs.getInt("idOtroTrato"),
                            rs.getInt("idProducto"),
                            rs.getString("fecha"),
                            rs.getFloat("precio"),
                            rs.getString("comentario"),
                            rs.getInt("puntuacion"),
                            rs.getInt("completado"));
                    tratos.add(trato);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tratos;
    }

}
