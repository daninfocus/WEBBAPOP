package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Clase Usuario.
 */
public class Usuario implements Serializable {

    //Atributos de la clase usuario.
    private boolean estaLogueado; // Atributo para saber si el usuario está logueado o no
    private int id; // Identificador del usuario
    private String nombre; // Nombre del usuario
    private String apellidos; // Apellidos del usuario
    private String direccion; // Direccion donde reside el usuario, para saber desde donde vende o compra
    private String telefono; // Numero de movil, para poder comunicarte con otros usuarios
    private String sexo; // Sexo del usuario (Hombre, Mujer, etc)
    private String email; // Email del usuario para una notificacion de cuando ha vendido o a comprado un producto
    private String fecha_nacimiento; // Fecha en la que ha nacido el usuario
    private String password; // Contraseña que introduce el usuario cuando va a registrarse y al iniciar sesion.
    private int valoracionesPendientes;
    private double notaMedia;
    /**
     * Creamos un Constructor de usuario para poder instanciarlo pasandole
     * los parametros que se muestran a continuacion.
     *
     * @param nombre           Nombre del usuario
     * @param apellidos        Apellidos del usuario
     * @param direccion        Direccion donde reside el usuario, para saber desde donde vende o compra
     * @param telefono         Numero de movil, para poder comunicarte con otros usuarios
     * @param sexo             Sexo del usuario (Hombre, Mujer, etc)
     * @param email            Email del usuario para una notificacion de cuando ha vendido o a comprado un producto
     * @param fecha_nacimiento Fecha en la que ha nacido el usuario
     * @param password         Contraseña que introduce el usuario cuando va a registrarse y al iniciar sesion.
     */

    public Usuario(int id, String nombre, String apellidos, String direccion, String telefono, String sexo, String email, String fecha_nacimiento, String password, double notaMedia) {
        estaLogueado = false;
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.sexo = sexo;
        this.email = email;
        this.fecha_nacimiento = fecha_nacimiento;
        this.password = password;
        this.notaMedia=notaMedia;
    }

    /**
     * Constructor copia de usuario.
     */
    public Usuario() {
    }
    public boolean isEstaLogueado() {
        return estaLogueado;
    }

    public void setEstaLogueado(boolean estaLogueado) {
        this.estaLogueado = estaLogueado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public int getValoracionesPendientes() {
        return valoracionesPendientes;
    }

    public void setValoracionesPendientes(int valoracionesPendientes) {
        this.valoracionesPendientes = valoracionesPendientes;
    }

    @Override
    public String toString() {
        return "\n■■■■■■■■■■ INFORMACIÓN BÁSICA ■■■■■■■■■■■\n\n" +
                "▲ Nombre: " + nombre + "\n" +
                "▲ Apellidos: " + apellidos + "\n" +
                "▲ Dirección: " + direccion + "\n" +
                "▲ Número de Teléfono: " + telefono + "\n\n" +
                "■■■■■■■■■■ INFORMACIÓN PRIVADA ■■■■■■■■■■\n\n" +
                "▲ Valoración media: " + (notaMedia == 0 ? "0" : notaMedia) + "\n" +
                "▲ Identificador: " + id + "\n" +
                "▲ Sexo: " + sexo + "\n" +
                "▲ Email: " + email + "\n" +
                "▲ Contraseña: " + password + "\n" +
                "▲ Fecha de Nacimiento: " + fecha_nacimiento;
    }


    /**
     * Validar email boolean.
     *
     * @param email the email
     * @return the boolean
     */
// Otros Métodos.
    public static boolean validarEmail(String email) {
        /* Este metodo valida un Email introducido como String comprobando
        que el usuario haya introducido una arroba y minimo tenga un punto. */
        boolean arroba = false, punto = false;
        int posicionArroba = 0, contArroba = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                arroba = true;
                posicionArroba = i;
                contArroba++;
            }
        }

        if (arroba && contArroba == 1) {
            for (int i = posicionArroba; i < email.length(); i++) {
                if (email.charAt(i) == '.') {
                    punto = true;
                    break;
                }
            }
        }

        return (arroba && punto);
    }

    /**
     * Validar password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean validarPassword(String password) {
        /*
        Este método lo que hace es recorrer los Strings para comprobar si alguna letra mayúscula o minúscula o número coincide con
        los caracteres de la contraseña.
         */
        String letrasMayusculas = "QWERTYUIOPASDFGHJKLÑZXCVBNM";
        String letrasMinisculas = "qwertyuiopasdfghjklñzxcvbnm";
        String numeros = "1234567890";
        boolean minuscula = false, mayuscula = false, numero = false;
        if (password.length() >= 8) {
            for (int i = 0; i < password.length(); i++) {
                for (int m = 0; m < letrasMayusculas.length(); m++) {
                    if (password.charAt(i) == letrasMayusculas.charAt(m)) {
                        mayuscula = true;
                        break;
                    }
                }
                for (int n = 0; n < letrasMinisculas.length(); n++) {
                    if (password.charAt(i) == letrasMinisculas.charAt(n)) {
                        minuscula = true;
                        break;
                    }
                }
                for (int z = 0; z < numeros.length(); z++) {
                    if (password.charAt(i) == numeros.charAt(z)) {
                        numero = true;
                        break;
                    }
                }
            }
        }
        return (mayuscula && numero && minuscula);
    }


    /**
     * Validar fecha boolean.
     *
     * @param fecha the fecha
     * @return the boolean
     */
    public static boolean validarFecha(String fecha) {
        /* Este metodo recibe una fecha de tipo String y lo que hace es formatearla al formato de dia/mes/año
        Si hace el formateo correctamente devuelve true (eso quiere decir que la fecha es correcta) si la fecha
        no es correcta, devuelve false (lo que quiere decir es que la fecha no se ha podido formatear correctamente) */
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate d = LocalDate.parse(fecha, formatter);
            LocalDate today = LocalDate.now();
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }

    }
}