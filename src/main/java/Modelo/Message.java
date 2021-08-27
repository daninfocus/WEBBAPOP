package Modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Message implements Comparable<Object>, Serializable {
    private int ID_Message;
    private int ID_User_Sender;
    private int ID_User_Reciever;
    private int ID_Product;
    private String Sent_Date;
    private String Recieved_Date;
    private String Message;
    private boolean	Message_Read; //1 for read 0 for sent

    //Constructor

    public Message(int ID_User_Sender,int ID_User_Reciever, int ID_Product, String sent_Date, String message) {
        this.ID_User_Sender = ID_User_Sender;
        this.ID_User_Reciever = ID_User_Reciever;
        this.ID_Product = ID_Product;
        Sent_Date = sent_Date;
        Message = message;
    }


    //Getter y Setter

    public int getID_Message() {
        return ID_Message;
    }

    public void setID_Message(int ID_Message) {
        this.ID_Message = ID_Message;
    }

    public int getID_User_Sender() {
        return ID_User_Sender;
    }

    public void setID_User_Sender(int ID_User_Sender) {
        this.ID_User_Sender = ID_User_Sender;
    }

    public int getID_User_Reciever() {
        return ID_User_Reciever;
    }

    public void setID_User_Reciever(int ID_User_Reciever) {
        this.ID_User_Reciever = ID_User_Reciever;
    }

    public String getSent_Date() {
        return Sent_Date;
    }

    public void setSent_Date(String sent_Date) {
        Sent_Date = sent_Date;
    }

    public String getRecieved_Date() {
        return Recieved_Date;
    }

    public void setRecieved_Date(String recieved_Date) {
        Recieved_Date = recieved_Date;
    }

    public boolean isMessage_Read() {
        return Message_Read;
    }

    public void setMessage_Read(boolean message_Read) {
        Message_Read = message_Read;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getID_Product() {
        return ID_Product;
    }

    public void setID_Product(int ID_Product) {
        this.ID_Product = ID_Product;
    }

    @Override
    public int compareTo(Object o) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = new GregorianCalendar();
        sdf.format(Sent_Date);
        Message aux = (Message) o;

        return Sent_Date.compareTo(aux.getSent_Date());
    }
}

