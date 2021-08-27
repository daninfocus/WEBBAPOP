package DAO;

import Modelo.Producto;

import java.util.ArrayList;

public interface DaoProducto {
    public boolean insert(Producto producto, DAOManager dao);

    public boolean update(Producto producto, DAOManager dao);

    public boolean delete(int id, DAOManager dao);

    public Producto read(int id, DAOManager dao);

    public ArrayList<Producto> getAll(DAOManager dao);

    public ArrayList<Producto> getAllFromUser(int id, DAOManager dao);
}
