package models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles")
public class Detalle implements Serializable {

    @EmbeddedId
    private DetalleId id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    Pedido pedido;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    Producto producto;

    @Column(name = "precio_unidad")
    private BigDecimal precioUnidad;

    @Column
    private Short cantidad;

    @Column
    private Double descuento;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

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