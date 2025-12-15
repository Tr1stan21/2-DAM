package model;

import java.math.BigDecimal;

public class Existencias {

    private String codArticulo;
    private String nombre;
    private int cantidad;
    private BigDecimal precio;
    private String codRest;

    public Existencias() {
    }

    public Existencias(String codArticulo, String nombre, int cantidad,
                       BigDecimal precio, String codRest) {
        this.codArticulo = codArticulo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.codRest = codRest;
    }

    public String getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(String codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getCodRest() {
        return codRest;
    }

    public void setCodRest(String codRest) {
        this.codRest = codRest;
    }
}

