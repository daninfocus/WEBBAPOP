package DAO;

import Modelo.Trato;

import java.sql.*;
import java.util.ArrayList;

public class TratoSQL implements DaoTrato {
    @Override
    public boolean insert(Trato trato, DAOManager dao) {
        int result = 0;
        try {
            PreparedStatement ps = dao.getConn().prepareStatement("INSERT INTO Trato VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1,trato.getId());
            ps.setString(2,trato.getTipoTrato());
            ps.setString(3,trato.getEmailUsuarioTrato());
            ps.setString(4, trato.getEmailUsuarioOtro());
            ps.setInt(5,trato.getIdProducto());
            ps.setString(6,trato.getFecha());
            ps.setFloat(7,trato.getPrecio());
            ps.setString(8,trato.getComentario());
            ps.setInt(9,trato.getPuntuacion());
            ps.setInt(10,trato.getCompletado());
            result = ps.executeUpdate();
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
                            rs.getString("emailUsuarioTrato"),
                            rs.getString("emailUsuarioOtro"),
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
    public ArrayList<Trato> readPendiente(int idUsuarioVendedor, DAOManager dao) {
        Trato trato = null;
        String sentencia;
        ArrayList<Trato> tratos = new ArrayList<>();
        sentencia = "select * from Trato where idUsuarioVendedor = ? and tipoTrato like 'Compra'";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setString(1, String.valueOf(idUsuarioVendedor));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    trato = new Trato(
                            rs.getInt("id"),
                            rs.getString("tipoTrato"),
                            rs.getString("emailUsuarioTrato"),
                            rs.getString("emailUsuarioOtro"),
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
    public ArrayList<Trato> getAll(String emailUsuarioTrato, DAOManager dao) {
        Trato trato = null;
        String sentencia;
        ArrayList<Trato> tratos = new ArrayList<>();
        sentencia = "select * from Trato where emailUsuarioTrato = ?";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setString(1, String.valueOf(emailUsuarioTrato));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    trato = new Trato(
                            rs.getInt("id"),
                            rs.getString("tipoTrato"),
                            rs.getString("emailUsuarioTrato"),
                            rs.getString("emailUsuarioOtro"),
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
