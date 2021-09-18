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
            sentencia = "INSERT INTO Messages (ID_User_Sender,ID_User_Reciever,ID_Product,ID_Chat,Message,Sent_Date,Recieved_Date,Message_Read,Message_Deleted) VALUES ('"
                    + message.getID_User_Sender() + "','"
                    + message.getID_User_Reciever() + "','"
                    + message.getID_Product() + "','"
                    + message.getID_Chat() + "','"
                    + message.getMessage() + "','"
                    + message.getSent_Date() + "','"
                    + message.getRecieved_Date() + "','"
                    + message.isMessage_Read() + "','"
                    + message.isMessage_Deleted() + "');";

            st.executeUpdate(sentencia);
            st.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Message message, DAOManager dao) {
        String sentencia;
        Connection conn = dao.getConn();
        sentencia = "UPDATE Messages SET ID_Message='" + message.getID_Message() + "', ID_User_Sender = '"
                + message.getID_User_Sender() + "', ID_User_Reciever = '"
                + message.getID_User_Reciever() + "', ID_Product = '"
                + message.getID_Product() + "', ID_Chat = '"
                + message.getID_Chat() + "', Message = '"
                + message.getMessage() + "', Sent_Date = '"
                + message.getSent_Date() + "', Recieved_Date = '"
                + message.getRecieved_Date() + "', Message_Read = '"
                + message.isMessage_Read() + "', Message_Deleted = '"
                + message.isMessage_Deleted() + "' WHERE ID_Message='" + message.getID_Message() + "';";

        try (PreparedStatement stmt = conn.prepareStatement(sentencia)) {
            // enviar el commando insert
            stmt.executeUpdate(sentencia);
            conn.close();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int ID_Message, DAOManager dao) {

        try {
            PreparedStatement ps = dao.getConn().prepareStatement("UPDATE Messages SET Message_Deleted = 1 WHERE ID_Message = ?");
            ps.setInt(1, ID_Message);

            // enviar el commando insert
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Message> readMessages(int ID_User, int ID_Product, DAOManager dao) {
        Message message = null;
        ArrayList<Message> messages = new ArrayList<>();
        String sentencia;
        sentencia = "SELECT * from Messages where ID_Product = ? and ID_User_Sender = ? or ID_User_Reciever = ?";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setInt(1, ID_Product);
            ps.setInt(2, ID_User);
            ps.setInt(3, ID_User);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Message
                    //System.out.println(rs.getInt("ID_Message"));
                    message = new Message(
                            rs.getInt("ID_Message"),
                            rs.getInt("ID_User_Sender"),
                            rs.getInt("ID_User_Reciever"),
                            rs.getInt("ID_Product"),
                            rs.getInt("ID_Chat"),
                            rs.getString("Sent_Date"),
                            rs.getString("Recieved_Date"),
                            rs.getString("message"),
                            rs.getInt("Message_Read"),
                            rs.getInt("Message_Deleted"));
                    messages.add(message);
                }
                ps.close();

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
                    if (!product_ids.contains(rs.getInt("ID_Product"))) {
                        product_ids.add(rs.getInt("ID_Product"));
                    }
                }
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product_ids;
    }

    @Override
    public ArrayList<Message> getAllMessagesFromChat(int ID_Chat, DAOManager dao) {
        String sentencia = "SELECT * FROM Messages WHERE ID_Chat = " + ID_Chat;

        ArrayList<Message> messages = new ArrayList<>();

        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Message message = new Message(
                            rs.getInt("ID_Message"),
                            rs.getInt("ID_User_Sender"),
                            rs.getInt("ID_User_Reciever"),
                            rs.getInt("ID_Product"),
                            rs.getInt("ID_Chat"),
                            rs.getString("Sent_Date"),
                            rs.getString("Recieved_Date"),
                            rs.getString("Message"),
                            rs.getInt("Message_Read"),
                            rs.getInt("Message_Deleted")

                    );
                    if(messages!=null) {
                        messages.add(message);
                    }

                }
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return messages;
    }


    @Override
    public ArrayList<Message> getAll( DAOManager dao) {
        String sentencia = "SELECT * FROM Messages";

        ArrayList<Message> messages = new ArrayList<>();

        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Message message = new Message(
                            rs.getInt("ID_Message"),
                            rs.getInt("ID_User_Sender"),
                            rs.getInt("ID_User_Reciever"),
                            rs.getInt("ID_Product"),
                            rs.getInt("ID_Chat"),
                            rs.getString("Sent_Date"),
                            rs.getString("Recieved_Date"),
                            rs.getString("Message"),
                            rs.getInt("Message_Read"),
                            rs.getInt("Message_Deleted")
                    );
                    messages.add(message);
                }
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return messages;
    }
}

