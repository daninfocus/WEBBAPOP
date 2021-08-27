package DAO;

import Modelo.Message;
import Modelo.Producto;

import java.util.ArrayList;

public interface DaoMessage {
    public boolean insert(Message message, DAOManager dao);

    public boolean update(Message message, DAOManager dao);

    public boolean delete(int ID_Message, DAOManager dao);

    public ArrayList<Message> readMessages(int ID_User, int ID_Product, DAOManager dao);

    public ArrayList<Integer> getAllChats(int ID_User,DAOManager dao);
}
