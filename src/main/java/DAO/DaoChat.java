package DAO;

import Modelo.Chat;

import java.util.ArrayList;

public interface DaoChat {
    public boolean insert(int ID_chat, int ID_user,int id_prod,DAOManager dao);
    public boolean delete(int ID_chat, int ID_user,DAOManager dao);
    public ArrayList<Chat> read(int ID_user, DAOManager dao);
    public ArrayList<Chat> getChat(int ID_Chat, DAOManager dao);
    public ArrayList<Chat> getAll( DAOManager dao);
}
