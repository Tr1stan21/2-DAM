package models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Detalle implements Serializable {
    private DetalleId id;
    private BigDecimal precioUnidad;
    private Short cantidad;
    private Double descuento;

    public Detalle() {}

    public Detalle(DetalleId id, BigDecimal precioUnidad, Short cantidad, Double descuento) {
        this.id = id;
        this.precioUnidad = precioUnidad;
        this.cantidad = cantidad;
        this.descuento = descuento;
    }

    public DetalleId getId() { return id; }
    public void setId(DetalleId id) { this.id = id; }

    public BigDecimal getPrecioUnidad() { return precioUnidad; }
    public void setPrecioUnidad(BigDecimal precioUnidad) { this.precioUnidad = precioUnidad; }

    public Short getCantidad() { return cantidad; }
    public void setCantidad(Short cantidad) { this.cantidad = cantidad; }

    public Double getDescuento() { return descuento; }
    public void setDescuento(Double descuento) { this.descuento = descuento; }

    @Override
    public String toString() {
        return "Detalle{" +
                "id=" + id +
                ", precioUnidad=" + precioUnidad +
                ", cantidad=" + cantidad +
                ", descuento=" + descuento +
                '}';
    }
}