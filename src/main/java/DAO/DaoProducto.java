package DAO;

import Modelo.Producto;

import java.io.InputStream;
import java.util.ArrayList;

public interface DaoProducto {
    public boolean insert(Producto producto, InputStream blob, DAOManager dao);

    public boolean update(Producto producto, DAOManager dao);

    public boolean delete(int id, DAOManager dao);

    public Producto read(int id, DAOManager dao);

    public ArrayList<Producto> getAll(DAOManager dao);

    public ArrayList<Producto> getAllFromUser(int id, DAOManager dao);

    public boolean sell(int productID, DAOManager dao);

    public ArrayList<Producto> searchByText(String text, DAOManager dao);
}
