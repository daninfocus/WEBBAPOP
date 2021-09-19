package Utils;

import Modelo.GestionAPP;
import Modelo.Producto;
import Modelo.Usuario;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Properties;

import static javax.mail.Transport.send;

public class Notificaciones {


    private static String host = "";
    private static String user = "";
    private static String pass = "";
    private static String emisor = "";


    public Notificaciones() {
        GestionAPP gestion = new GestionAPP();
        try {
            Properties props = new Properties();
            props.load(new FileReader(gestion.getPropertiesFile()));
            host = props.getProperty("host");
            user = props.getProperty("user");
            pass = props.getProperty("pass");
            emisor = props.getProperty("emisor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Boolean enviaTokenRegistro(String destino, int token) {
        String asunto = "Confirmación de registro de nuevo usuario. ";
        boolean resultado;
        resultado = false;
        String contenido;

        // Creamos nuestra variable de propiedades con los datos de nuestro servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            // Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);

            // En el mensaje, establecemos el emisor con los datos pasados a la función
            message.setFrom(new InternetAddress(emisor));

            // En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));

            // Establecemos el Asunto
            message.setSubject(asunto);

            // Añadimos el contenido del mensaje
            //message.setText(mensaje);  Si solo mandamos texto
            contenido =
                    "<div ALIGN=\"center\"> <img src=\"https://i.postimg.cc/CL8ZxhKm/background.png\" alt=\"banner de la empresa\" height=\"270\" width=\"750\"></div>\n" +
                            "\n" +
                            "    <br> <br> <br>\n" +
                            "    <div ALIGN=\"center\"><h1 style=\"color: #05963a; font-family: Arial, Helvetica, sans-serif; text-decoration: underline;\"> <strong> CODIGO DE VERIFICACION </strong> </h1>\n" +
                            "\n" +
                            "        <h4 style=\"font-family: Arial, Helvetica, sans-serif; line-height: 150%;\"> Bienvenido querido usuario de Webbapop. Gracias por utilizar nuestra aplicacion. <br> <br>\n" +
                            "    \n" +
                            "            Para terminar su registro tiene que introducir un codigo proporcionado a continuacion. <br> \n" +
                            "    \n" +
                            "            !Este es el codigo que debes de introducir¡. Gracias por su colaboracion. <br>\n" +
                            "    \n" +
                            "\n" +
                            "<div ALIGN=\"center\">\n" +
                            "    <br>\n" +
                            "        <p>\n" +
                            "            <div style=\"background-color: #05963a; padding: 15px; margin-right: 30%; margin-left: 30%;\">\n" +
                            "                <h3 style=\"text-align: center; font-family: Arial, Helvetica, sans-serif;\">\n" +
                            "                    <strong>\n" +
                            "                        <font style=\"color: black; font-size: 40px;\">\n" +
                            "                             " + token + "\n" +
                            "                        </font>\n" +
                            "                    </strong>\n" +
                            "                </h3>\n" +
                            "            </div>\n" +
                            "        </p>\n" +
                            "        <br>\n" +
                            " </div>\n" +
                            "\n" +
                            "        Atentamente el equipo de desarrollo. </h4> </div>\n" +
                            "\n" +
                            "\n" +
                            "        <h6 style=\"text-align: left; margin-top: 50px;\">Todos los derechos reservados al equipo de desarrollo de Webbapop © ®</h6>";

            message.setContent(contenido, "text/html; charset=utf-8");

            // Intenamos mandar el mensaje
            send(message);
            resultado = true;

        } catch (Exception e) { //Si entra aquí hemos tenido fallo
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public static Boolean enviaTokenBorrar(Usuario u1, String token) {
        String destino = u1.getEmail();
        String asunto = "Confirmación de eliminar perfil de usuario. ";
        boolean resultado;
        resultado = false;
        String contenido;

        // Creamos nuestra variable de propiedades con los datos de nuestro servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            // Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);

            // En el mensaje, establecemos el emisor con los datos pasados a la función
            message.setFrom(new InternetAddress(emisor));

            // En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));

            // Establecemos el Asunto
            message.setSubject(asunto);

            // Añadimos el contenido del mensaje
            //message.setText(mensaje);  Si solo mandamos texto
            contenido =
                    "<div ALIGN=\"center\"> <img src=\"https://i.postimg.cc/CL8ZxhKm/background.png\" alt=\"banner de la empresa\" height=\"270\" width=\"750\"></div>\n" +
                            "\n" +
                            "    <br> <br> <br>\n" +
                            "    <div ALIGN=\"center\"><h1 style=\"color: #05963a; font-family: Arial, Helvetica, sans-serif; text-decoration: underline;\"> <strong> CÓDIGO DE VERIFICACIÓN </strong> </h1>\n" +
                            "\n" +
                            "        <h4 style=\"font-family: Arial, Helvetica, sans-serif; line-height: 150%;\"> Bienvenido querido usuario de Webbapop. Gracias por utilizar nuestra aplicación. <br> <br>\n" +
                            "    \n" +
                            "            Para terminar su eliminación de su perfil de usuario tiene que introducir un código proporcionado a continuación. <br> \n" +
                            "    \n" +
                            "            !Este es el código que debes de introducir¡. Gracias por su colaboración. <br>\n" +
                            "    \n" +
                            "\n" +
                            "<div ALIGN=\"center\">\n" +
                            "    <br>\n" +
                            "        <p>\n" +
                            "            <div style=\"background-color: #05963a; padding: 15px; margin-right: 30%; margin-left: 30%;\">\n" +
                            "                <h3 style=\"text-align: center; font-family: Arial, Helvetica, sans-serif;\">\n" +
                            "                    <strong>\n" +
                            "                        <font style=\"color: black; font-size: 40px;\">\n" +
                            "                             " + token + "\n" +
                            "                        </font>\n" +
                            "                    </strong>\n" +
                            "                </h3>\n" +
                            "            </div>\n" +
                            "        </p>\n" +
                            "        <br>\n" +
                            " </div>\n" +
                            "\n" +
                            "        Atentamente el equipo de desarrollo. </h4> </div>\n" +
                            "\n" +
                            "\n" +
                            "        <h6 style=\"text-align: left; margin-top: 50px;\">Todos los derechos reservados al equipo de desarrollo de Webbapop © ®</h6>       ";

            message.setContent(contenido, "text/html; charset=utf-8");

            // Intenamos mandar el mensaje
            send(message);
            resultado = true;

        } catch (Exception e) { //Si entra aquí hemos tenido fallo
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public static boolean enviaMensajeTelegram(String nombreUsuario, String nombreProducto) {
        String fijo = "https://api.telegram.org/bot919969777:AAFGsMiuJGmXgFg19R2GXIpT8uWUfrfRm1I/sendMessage?chat_id=689390668&text=";
        String mensaje = "El usuario " + nombreUsuario.toUpperCase() + " ha registrado el producto " + nombreProducto.toUpperCase();
        String direccion = fijo + mensaje;  //Metemos el mensaje al final

        String fijo2 = "https://api.telegram.org/bot1554890635:AAEt73VAZxGYybTG7NGZB-riajiAZ1AN_3c/sendMessage?chat_id=548604744&text=";
        String direccion2 = fijo2 + mensaje;  //Metemos el mensaje al final
        URL url, url2;
        boolean dev;
        dev = false;

        try {
            url = new URL(direccion); // Creando un objeto URL con la dirección de la API de Jose
            URLConnection con = url.openConnection(); // Realizando la petición GET
            //Con esto, copiamos en in la respuesta HTTP , por si lo necesitamos
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            url2 = new URL(direccion2); // Creando un objeto URL con la dirección de la API de Dani
            URLConnection con2 = url2.openConnection(); // Realizando la petición GET
            //Con esto, copiamos en in la respuesta HTTP , por si lo necesitamos
            BufferedReader in2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));

            dev = true; // Ha tenido éxito

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return dev; //Devuelvo si ha tenido éxito o no
    }

    public static Boolean enviaMailProductoRegistrado(Usuario usuario, Producto producto) {
        String destino = usuario.getEmail();
        String asunto = "Nuevo Producto registrado";
        boolean resultado;
        resultado = false;

        // Creamos nuestra variable de propiedades con los datos de nuestro servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            // Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);
            // En el mensaje, establecemos el emisor con los datos pasados a la función
            message.setFrom(new InternetAddress(emisor));
            // En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));
            // Establecemos el Asunto
            message.setSubject(asunto);
            // Añadimos el contenido del mensaje
            //message.setText(mensaje);  Si solo mandamos texto

            String contenido =
                    "<div ALIGN=\"center\"> <img src=\"https://i.postimg.cc/CL8ZxhKm/background.png\" alt=\"banner de la empresa\" height=\"270\" width=\"750\"></div>\n" +
                            "\n" +
                            "    <br> <br> <br>\n" +
                            "    <div ALIGN=\"center\"><h1 style=\"color: #05963a; font-family: Arial, Helvetica, sans-serif; text-decoration: underline;\"> <strong> NUEVO PRODUCTO REGISTRADO </strong> </h1>\n" +
                            "\n" +
                            "        <h4 style=\"font-family: Arial, Helvetica, sans-serif; line-height: 150%;\">" + usuario.getNombre() + " has registrado un nuevo producto. <br> <br>\n" +
                            "    \n" +
                            "            A continuación te mostramos toda la información del producto que usted <br> ha registrado en la aplicación de Webbapop. Gracias por su colaboración. <br>\n" +
                            "    \n" +
                            "\n" +
                            "<div ALIGN=\"center\">\n" +
                            "    <br>\n" +
                            "        <p>\n" +
                            "            <div style=\"background-color: #05963a; padding: 15px; margin-right: 18%; margin-left: 18%;\">\n" +
                            "                <h3 style=\"text-align: center; font-family: Arial, Helvetica, sans-serif;\">\n" +
                            "                    <strong>\n" +
                            "                        <font style=\"color: black; font-size: 12px;\">\n" +
                            "                            NOMBRE: " + producto.getNombre() + " DESCRIPCION: " + producto.getDescripcion() + " CATEGORIA: " + producto.getCategoria() + "<br>\n" +
                            "                            ESTADO: " + producto.getEstado() + " PRECIO: " + producto.getPrecio() + "€" + " FECHA DE VENTA: " + producto.getFecha() + "<br>\n" +
                            "                        </font>\n" +
                            "                    </strong>\n" +
                            "                </h3>\n" +
                            "            </div>\n" +
                            "        </p>\n" +
                            "        <br>\n" +
                            " </div>\n" +
                            "\n" +
                            "        Atentamente el equipo de desarrollo. </h4> </div>\n" +
                            "\n" +
                            "\n" +
                            "        <h6 style=\"text-align: left; margin-top: 50px;\">Todos los derechos reservados al equipo de desarrollo de Webbapop © ®</h6>";
            message.setContent(contenido, "text/html; charset=utf-8");

            // Intenamos mandar el mensaje
            Transport.send(message);
            resultado = true;

        } catch (Exception e) { //Si entra aquí hemos tenido fallo
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public static Boolean enviaJson(String gson, String email) {
        String destino = email;
        String asunto = "JSON de su usuario";
        SimpleDateFormat hms = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat dMy = new SimpleDateFormat("dd/MM/yyyy");
        boolean resultado;
        resultado = false;
        // Creamos nuestra variable de propiedades con los datos de nuestro servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        // Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        try {
            // Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);
            // En el mensaje, establecemos el emisor con los datos pasados a la función
            message.setFrom(new InternetAddress(emisor));
            // En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));
            // Establecemos el Asunto
            message.setSubject(asunto);
            // Añadimos el contenido del mensaje
            //message.setText(mensaje);  Si solo mandamos texto

            String contenido =
                    "<div ALIGN=\"center\"> <img src=\"https://i.postimg.cc/hG9Zy2pK/Webbapop.png\" alt=\"banner de la empresa\" height=\"270\" width=\"750\"></div>\n" +
                            "\n" +
                            "    <br> <br> <br>\n" +
                            "    <div ALIGN=\"center\"><h1 style=\"color: #05963a; font-family: Arial, Helvetica, sans-serif; text-decoration: underline;\"> <strong> JSON DE JOSE MANUEL GONZALEZ Y DANIEL WEBB </strong> </h1>\n" +
                            "\n" +
                            "        <h4 style=\"font-family: Arial, Helvetica, sans-serif; line-height: 150%;\"> Bienvenido querido usuario de Webbapop. Gracias por utilizar nuestra aplicación. <br> <br>\n" +
                            "    \n" +
                            "            <!-- A continuación le va a aparecer la inf Para terminar su eliminación de su perfil de usuario tiene que introducir un código proporcionado a continuación. <br> -->\n" +
                            "    \n" +
                            "            !Este es su fichero JSON donde aparece toda la informacion de su perfil¡. Gracias por su colaboración. <br><br><br>\n" +
                            "\n" +
                            "            " + gson + "\n" +
                            "        \n" +
                            "    \n" +
                            "\n" +
                            "<!-- <div ALIGN=\"center\">\n" +
                            "    <br>\n" +
                            "        <p>\n" +
                            "            <div style=\"background-color: #05963a; padding: 15px; margin-right: 30%; margin-left: 30%;\">\n" +
                            "                <p style=\"text-align: center; font-family: Arial, Helvetica, sans-serif;\">\n" +
                            "                    <strong>\n" +
                            "                        <font style=\"color: black; \">\n" +
                            "                             + gson + \n" +
                            "                        </font>\n" +
                            "                    </strong>\n" +
                            "                </p>\n" +
                            "            </div>\n" +
                            "        </p>\n" +
                            "        <br> -->\n" +
                            " </div> \n" +
                            "\n" +
                            "        Atentamente el equipo de desarrollo. </h4> </div>\n" +
                            "\n" +
                            "\n" +
                            "        <h6 style=\"text-align: left; margin-top: 50px;\">Todos los derechos reservados al equipo de desarrollo de Webbapop © ®</h6>";
            message.setContent(contenido, "text/html; charset=utf-8");
            // Intenamos mandar el mensaje
            Transport.send(message);
            resultado = true;
        } catch (Exception e) { //Si entra aquí hemos tenido fallo
            throw new RuntimeException(e);
        }
        return resultado;
    }


    public static Boolean enviaUsuarioExcel(String email) {
        String destino = email;
        String asunto = "Lista de usuarios";
        SimpleDateFormat hms = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat dMy = new SimpleDateFormat("dd/MM/yyyy");
        boolean resultado;
        resultado = false;
        // Creamos nuestra variable de propiedades con los datos de nuestro servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        // Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        try {
            // Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);
            // En el mensaje, establecemos el emisor con los datos pasados a la función
            message.setFrom(new InternetAddress(emisor));
            // En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));
            // Establecemos el Asunto
            message.setSubject(asunto);
            // Añadimos el contenido del mensaje
            //message.setText(mensaje);  Si solo mandamos texto

            String contenido =
                    "<div ALIGN=\"center\"> <img src=\"https://i.postimg.cc/hG9Zy2pK/Webbapop.png\" alt=\"banner de la empresa\" height=\"270\" width=\"750\"></div>\n" +
                            "\n" +
                            "    <br> <br> <br>\n" +
                            "    <div ALIGN=\"center\"><h1 style=\"color: #05963a; font-family: Arial, Helvetica, sans-serif; text-decoration: underline;\"> <strong> JSON DE JOSE MANUEL GONZALEZ Y DANIEL WEBB </strong> </h1>\n" +
                            "\n" +
                            "        <h4 style=\"font-family: Arial, Helvetica, sans-serif; line-height: 150%;\"> Bienvenido querido usuario de Webbapop. Gracias por utilizar nuestra aplicación. <br> <br>\n" +
                            "    \n" +
                            "            <!-- A continuación le va a aparecer la inf Para terminar su eliminación de su perfil de usuario tiene que introducir un código proporcionado a continuación. <br> -->\n" +
                            "    \n" +
                            "            !Este es su fichero JSON donde aparece toda la informacion de su perfil¡. Gracias por su colaboración. <br><br><br>\n" +
                            "\n" +

                            "        \n" +
                            "    \n" +
                            "\n" +
                            "<!-- <div ALIGN=\"center\">\n" +
                            "    <br>\n" +
                            "        <p>\n" +
                            "            <div style=\"background-color: #05963a; padding: 15px; margin-right: 30%; margin-left: 30%;\">\n" +
                            "                <p style=\"text-align: center; font-family: Arial, Helvetica, sans-serif;\">\n" +
                            "                    <strong>\n" +
                            "                        <font style=\"color: black; \">\n" +
                            "                             + gson + \n" +
                            "                        </font>\n" +
                            "                    </strong>\n" +
                            "                </p>\n" +
                            "            </div>\n" +
                            "        </p>\n" +
                            "        <br> -->\n" +
                            " </div> \n" +
                            "\n" +
                            "        Atentamente el equipo de desarrollo. </h4> </div>\n" +
                            "\n" +
                            "\n" +
                            "        <h6 style=\"text-align: left; margin-top: 50px;\">Todos los derechos reservados al equipo de desarrollo de Webbapop © ®</h6>";
            message.setContent(contenido, "text/html; charset=utf-8");


            MimeBodyPart messageBodyPart = new MimeBodyPart();

            Multipart multipart = new MimeMultipart();

            DataSource source = new FileDataSource("./src/Resources/Usuarios.xlsx");

            messageBodyPart.setDataHandler(new DataHandler(source));

            messageBodyPart.setFileName("Usuarios.xlsx");

            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            // Intenamos mandar el mensaje
            Transport.send(message);
            resultado = true;
        } catch (Exception e) { //Si entra aquí hemos tenido fallo
            throw new RuntimeException(e);
        }


        return resultado;
    }
}
