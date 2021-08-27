package DAO;

import Modelo.Message;
import Modelo.Producto;

import java.sql.*;
import java.util.ArrayList;

public class MessageSQL implements DaoMessage {
    @Override
    public boolean insert(Message message, DAOManager dao) {
        String sentencia;
        Connection conn = dao.getConn();
        try {
            Statement st = conn.createStatement();
            sentencia = "INSERT INTO Messages (ID_User_Sender,ID_User_Reciever,ID_Product,Message,Sent_Date) VALUES ('"
                    + message.getID_User_Sender() + "','"
                    + message.getID_User_Reciever() + "','"
                    + message.getID_Product() + "','"
                    + message.getMessage() + "','"
                    + message.getSent_Date() + "');";

            st.executeUpdate(sentencia);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Message message, DAOManager dao) {
        return false;
    }

    @Override
    public boolean delete(int ID_Message, DAOManager dao) {

        String sentencia = "DELETE FROM Message WHERE ID_Message = '" + ID_Message + "';";

        try (Statement stmt = dao.getConn().createStatement()) {
            // enviar el commando delete
            stmt.executeUpdate(sentencia);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public ArrayList<Message> readMessages(int ID_User, int ID_Product, DAOManager dao) {
        Message message = null;
        ArrayList<Message> messages = new ArrayList<>();
        String sentencia;
        sentencia = "SELECT * from Messages where ID_User_Sender = ? or ID_User_Reciever = ? and ID_Product = ?";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setInt(1, ID_User);
            ps.setInt(2, ID_User);
            ps.setInt(3, ID_Product);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    message = new Message(
                            rs.getInt("ID_User_Sender"),
                            rs.getInt("ID_User_Reciever"),
                            rs.getInt("ID_Product"),
                            rs.getString("sent_Date"),
                            rs.getString("message"));
                    messages.add(message);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return messages;
    }


    @Override
    public ArrayList<Integer> getAllChats(int ID_User, DAOManager dao) {
        String sentencia = "SELECT ID_Product FROM Messages WHERE ID_User_Sender=" + ID_User + " OR ID_User_Reciever=" + ID_User;

        ArrayList<Integer> product_ids = new ArrayList<>();

        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if(!product_ids.contains(rs.getInt("ID_Product"))) {
                        product_ids.add(rs.getInt("ID_Product"));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product_ids;
    }
}

