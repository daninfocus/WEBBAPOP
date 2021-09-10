package DAO;

import Modelo.Trato;

import java.util.ArrayList;

public interface DaoTrato {
    public boolean insert (Trato trato, DAOManager dao);
    public boolean update (Trato trato, DAOManager dao);
    public boolean delete (Trato trato, DAOManager dao);
    public Trato read(int id, DAOManager dao);
    public ArrayList<Trato> readPendiente(int idUsuario, DAOManager dao);
    public ArrayList<Trato> getAll(String emailUsuarioTrato, DAOManager dao);
}
