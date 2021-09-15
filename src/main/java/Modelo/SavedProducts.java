package Modelo;

public class SavedProducts {
    private int Product_ID;
    private int User_ID;

    public SavedProducts(int product_ID, int user_ID) {
        Product_ID = product_ID;
        User_ID = user_ID;
    }

    public int getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(int product_ID) {
        Product_ID = product_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }
}
