package DAO;

import Modelo.Chat;
import Modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChatSQL implements DaoChat {

    @Override
    public boolean insert(int ID_chat, int ID_user, int ID_Product,DAOManager dao) {
        int result = 0;
        try {
            Connection con = dao.getConn();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Chats values(?,?,?,?)");
            //InputStream is = part.getInputStream();
            ps.setInt(1, 0);
            ps.setInt(2, ID_chat);
            ps.setInt(3, ID_user);
            ps.setInt(4, ID_Product);
            result = ps.executeUpdate();

            ps.close();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(int ID_chat, int ID_user, DAOManager dao) {
        int result = 0;
        try {
            Connection con = dao.getConn();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Chats WHERE ID_Chat = ? and ID_User = ?");
            //InputStream is = part.getInputStream();
            ps.setInt(1, ID_chat);
            ps.setInt(2, ID_user);
            result = ps.executeUpdate();

            ps.close();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    @Override
    public ArrayList<Chat> read(int ID_user, DAOManager dao) {
        ArrayList<Chat> chats = new ArrayList<>();
        Chat chat = null;
        String sentencia;
        sentencia = "select * from Chats where ID_User = ?";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setString(1, String.valueOf(ID_user));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    chat = new Chat(
                            rs.getInt("ID_Chat"),
                            rs.getInt("ID_User"),
                            rs.getInt("ID_Product"));
                }
                if(chat!=null){
                    chats.add(chat);
                }

                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return chats;
    }

    @Override
    public ArrayList<Chat> getChat(int ID_Chat, DAOManager dao) {
        ArrayList<Chat> chats = new ArrayList<>();
        Chat chat = null;
        String sentencia;
        sentencia = "select * from Chats where ID_Chat = ?";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setString(1, String.valueOf(ID_Chat));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    chat = new Chat(
                            rs.getInt("ID_Chat"),
                            rs.getInt("ID_User"),
                            rs.getInt("ID_Product"));
                    if(chat!=null){
                        chats.add(chat);
                    }
                }


                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return chats;
    }


    @Override
    public ArrayList<Chat> getAll(DAOManager dao) {
        ArrayList<Chat> chats = new ArrayList<>();
        Chat chat = null;
        String sentencia;
        sentencia = "select * from Chats";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);


            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    chat = new Chat(
                            rs.getInt("ID_Chat"),
                            rs.getInt("ID_User"),
                            rs.getInt("ID_Product"));
                }
                if(chat!=null) {
                    chats.add(chat);
                }
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return chats;
    }
}
