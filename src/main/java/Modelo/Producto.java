package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Producto.
 */
public class Producto implements Comparable<Object>, Serializable {

    // Atributos de la clase Producto
    private int id; // Numero unico del producto que sirve para identificar el producto dentro de todos los productos que haya a la venta
    private String nombre; // Nombre que va a tener tu producto cuando esté en venta. El que va a ver todos los usuarios
    private String descripcion; // Breve explicacion de que es tu producto
    private String categoria; // Categoria a la que pertenece el producto (Coche, Moto, Vivienda, etc)
    private float precio; // Precio que tiene el producto
    private String fecha; // Esta fecha es la fecha en la que el producto se pone en venta
    private String estado; // Estado en el que se encuentra el producto (Nuevo, Casi nuevo, etc)
    private int idUsuario;
    private int vendido;


    /**
     * Instantiates a new Producto.
     *
     * @param nombre      the nombre
     * @param descripcion the descripcion
     * @param categoria   the categoria
     * @param precio      the precio
     * @param estado      the estado
     */
    // Constructor con los atributos de la clase Producto
    public Producto(int id, String nombre, String descripcion, String categoria, float precio, String fecha, String estado, int idUsuario, int vendido) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.fecha = fecha;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.vendido = vendido;
    }

    // Getter and Setter de todos los atributos de la clase Producto
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getid() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    /*El metodo setFecha, lo que devuelve es la fecha actual con el formato indicado, para que el usuario sepa
    que dia puso en venta el producto */
    public void setFecha() {
        this.fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setUsuarioPropietario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getVendido() {
        return vendido;
    }

    public void setVendido(int vendido) {
        this.vendido = vendido;
    }

    @Override
    public String toString() {
        return "\n■■■■■■■■■■ DATOS DEL PRODUCTO ■■■■■■■■■■\n\n" +
                "▲ Identificador: " + id + "\n" + "▲ Nombre del producto: " + nombre + "\n" +
                "▲ Descripción del producto: " + descripcion + "\n" + "▲ Categoría del producto: " + categoria + "\n" +
                "▲ Estado del producto: " + estado + "\n" + "▲ Precio del producto: " + precio + " €" + "\n" + "▲ Fecha aniadido: " + fecha;
    }


    @Override
    public int compareTo(Object o) {
        Producto aux = (Producto) o;
        if (precio == aux.getPrecio()) return 0;
        if (precio > aux.getPrecio()) return 1;
        return -1;
    }
}
