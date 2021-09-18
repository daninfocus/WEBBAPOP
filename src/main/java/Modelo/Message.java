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
    private int ID_Chat;
    private String Sent_Date;
    private String Recieved_Date;
    private String Message;
    private int	Message_Read;
    private int Message_Deleted;

    //Constructor

    public Message(int ID_Message, int ID_User_Sender, int ID_User_Reciever, int ID_Product,int ID_Chat, String sent_Date, String recieved_Date, String message, int message_Read, int message_Deleted) {
        this.ID_Message = ID_Message;
        this.ID_User_Sender = ID_User_Sender;
        this.ID_User_Reciever = ID_User_Reciever;
        this.ID_Product = ID_Product;
        this.ID_Chat = ID_Chat;
        Sent_Date = sent_Date;
        Recieved_Date = recieved_Date;
        Message = message;
        Message_Read = message_Read;
        Message_Deleted = message_Deleted;
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

    public int isMessage_Read() {
        return Message_Read;
    }

    public void setMessage_Read(int message_Read) {
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

    public int isMessage_Deleted() {
        return Message_Deleted;
    }

    public void setMessage_Deleted(int message_Deleted) {
        Message_Deleted = message_Deleted;
    }

    public int getID_Chat() {
        return ID_Chat;
    }

    public void setID_Chat(int ID_Chat) {
        this.ID_Chat = ID_Chat;
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

