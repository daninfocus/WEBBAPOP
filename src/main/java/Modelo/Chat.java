package Modelo;

public class Chat {
    private int ID_Chat;
    private int ID_User;
    private int ID_Product;

    public Chat(int ID_Chat, int ID_User,int ID_Product) {
        this.ID_Chat = ID_Chat;
        this.ID_User = ID_User;
        this.ID_Product = ID_Product;
    }

    public int getID_Chat() {
        return ID_Chat;
    }

    public void setID_Chat(int ID_Chat) {
        this.ID_Chat = ID_Chat;
    }

    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }

    public int getID_Product() {
        return ID_Product;
    }

    public void setID_Product(int ID_Product) {
        this.ID_Product = ID_Product;
    }
}
