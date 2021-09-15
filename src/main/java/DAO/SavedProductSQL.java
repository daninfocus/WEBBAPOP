package DAO;

import Modelo.Message;
import Modelo.SavedProducts;

import java.sql.*;
import java.util.ArrayList;

public class SavedProductSQL implements DaoSavedProduct{
    @Override
    public boolean insert(SavedProducts product, DAOManager dao) {
        int result = 0;
        try {
            Connection con = dao.getConn();
            PreparedStatement ps = con.prepareStatement("INSERT INTO SavedProduct values(?,?,?)");
            //InputStream is = part.getInputStream();
            ps.setInt(1, 0);
            ps.setInt(2, product.getProduct_ID());
            ps.setInt(3, product.getUser_ID());

            result = ps.executeUpdate();
            con.commit();
            ps.close();
            if(result>0){
                return true;
            }else{
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(SavedProducts product, DAOManager dao) {
        String sentencia = "DELETE FROM SavedProduct WHERE User_ID = " + product.getUser_ID() + " and User_ID = " + product.getProduct_ID() + ";";
        int result =0;
        try (Statement stmt = dao.getConn().createStatement()) {
            Connection con = dao.getConn();
            PreparedStatement ps = con.prepareStatement("DELETE FROM SavedProduct WHERE Product_ID=? and User_ID=?");


            ps.setInt(1, product.getProduct_ID());
            ps.setInt(2, product.getUser_ID());

            result = ps.executeUpdate();
            con.commit();
            ps.close();
            if(result>0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public ArrayList<SavedProducts> read(int idUser, DAOManager dao) {
        Message message = null;
        ArrayList<SavedProducts> products = new ArrayList<>();
        String sentencia;
        sentencia = "SELECT * from SavedProduct where User_ID = ?";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setInt(1, idUser);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Message
                    //System.out.println(rs.getInt("ID_Message"));
                    SavedProducts product = new SavedProducts(
                            rs.getInt("Product_ID"),
                            rs.getInt("User_ID"));
                    products.add(product);
                }
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }
}
