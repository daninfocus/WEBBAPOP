package DAO;

import Modelo.SavedProducts;
import Modelo.Trato;

import java.util.ArrayList;

public interface DaoSavedProduct {
    public boolean insert (SavedProducts product, DAOManager dao);
    public boolean delete (SavedProducts product, DAOManager dao);
    public ArrayList<SavedProducts> read(int idUser, DAOManager dao);
}
