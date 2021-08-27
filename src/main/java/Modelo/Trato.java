package Modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * The type Trato.
 */
public class Trato implements Comparable<Object>, Serializable {

    private int id;
    private String tipoTrato; //Compra o Venta
    private int idUsuario; //id del usuario que tenga el trato
    private String emailOtroUsuario;
    private int idOtroTrato;
    private int idProducto;
    private String fecha;
    private float precio;
    private String comentario;
    private int puntuacion;
    private int completado;


    public Trato(int id, String tipoTrato, int idUsuario,String emailOtroUsuario, int idOtroTrato, int idProducto, String fecha, float precio, String comentario, int puntuacion, int completado) {
        this.id = id;
        this.tipoTrato = tipoTrato;
        this.emailOtroUsuario=emailOtroUsuario;
        this.idOtroTrato = idOtroTrato;
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.fecha = fecha;
        this.precio = precio;
        this.comentario = comentario;
        this.puntuacion = puntuacion;
        this.completado = completado;
    }

    /**
     * Gets id trato.
     *
     * @return the id trato
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id trato.
     *
     * @param id the id trato
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets tipo trato.
     *
     * @return the tipo trato
     */
    public String getTipoTrato() {
        return tipoTrato;
    }

    /**
     * Sets tipo trato.
     *
     * @param tipoTrato the tipo trato
     */
    public void setTipoTrato(String tipoTrato) {
        this.tipoTrato = tipoTrato;
    }


    /**
     * Gets idProducto.
     *
     * @return the idProducto
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * Sets idProducto.
     *
     * @param idProducto the idProducto
     */
    public void setidProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Gets fecha.
     *
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Sets fecha.
     *
     * @param fecha the fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Gets precio.
     *
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Sets precio.
     *
     * @param precio the precio
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * Gets comentario.
     *
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Sets comentario.
     *
     * @param comentario the comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Gets puntuacion.
     *
     * @return the puntuacion
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Sets puntuacion.
     *
     * @param puntuacion the puntuacion
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdOtroTrato() {
        return idOtroTrato;
    }

    public void setIdOtroTrato(int idOtroTrato) {
        this.idOtroTrato = idOtroTrato;
    }

    public int getCompletado() {
        return completado;
    }

    public void setCompletado(int completado) {
        this.completado = completado;
    }

    public String getEmailOtroUsuario() {
        return emailOtroUsuario;
    }

    public void setEmailOtroUsuario(String emailOtroUsuario) {
        this.emailOtroUsuario = emailOtroUsuario;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
// TODO: 12/05/2021 poner comprador y vendedos
        String apariencia = "\n■■■■■■■■■■ DATOS DEL TRATO ■■■■■■■■■■\n\n" +
                "▲ Identificador: " + id + "\n" +
                "▲ Tipo: " + tipoTrato + "\n" +
                ((tipoTrato.equalsIgnoreCase("Compra")) ? "▲ Vendedor: " +emailOtroUsuario :
                        "▲ Comprador: "+emailOtroUsuario) + "\n\n" +
                "▲ idProducto: " + idProducto + "\n" +
                "▲ Fecha y Hora: " + fecha + "\n" +
                "▲ Precio: " + precio + "\n" +
                "▲ Comentario: " + ((comentario.equals("") ? "Comentario no valorado. " : comentario)) + "\n" +
                "▲ Puntuación: ";

        if (puntuacion == -1) apariencia += "Puntuación no valorada. ";
        if (puntuacion == 0) apariencia += "0";
        if (puntuacion == 1) apariencia += valoracionDibujo(puntuacion);
        if (puntuacion == 2) apariencia += valoracionDibujo(puntuacion);
        if (puntuacion == 3) apariencia += valoracionDibujo(puntuacion);
        if (puntuacion == 4) apariencia += valoracionDibujo(puntuacion);
        if (puntuacion == 5) apariencia += valoracionDibujo(puntuacion);

        return apariencia;
    }

    /**
     * Valoracion dibujo string.
     *
     * @param valoracion the valoracion
     * @return the string
     */
    public static String valoracionDibujo(int valoracion) {
        String estrella1 = "★";
        String estrella2 = "★ ★";
        String estrella3 = "★ ★ ★";
        String estrella4 = "★ ★ ★ ★";
        String estrella5 = "★ ★ ★ ★ ★";

        if (valoracion == 1) return estrella1;
        if (valoracion == 2) return estrella2;
        if (valoracion == 3) return estrella3;
        if (valoracion == 4) return estrella4;
        if (valoracion == 5) return estrella5;

        return "Error en valoracion dibujo";
    }

    @Override
    public int compareTo(Object o) {
        Trato trato = (Trato) o;
// TODO: 12/05/2021 compareto with String dar
       /* if (fecha > (trato.getFecha().getTimeInMillis())) return 1;
        else if (fecha.getTimeInMillis() < (trato.getFecha().getTimeInMillis())) return -1;
        else return 0;
        */
        return 0;
    }
}
