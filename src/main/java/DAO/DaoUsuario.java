package DAO;

import Modelo.Usuario;

import java.util.ArrayList;

public interface DaoUsuario {
    public boolean insert(Usuario usuario, DAOManager dao);

    public boolean update(Usuario usuario, DAOManager dao);

    public Usuario read(String email, DAOManager dao);

    public Usuario readID(int id, DAOManager dao);

    public boolean delete(int id, DAOManager dao);

    public ArrayList<Usuario> getAll(DAOManager dao);
}
