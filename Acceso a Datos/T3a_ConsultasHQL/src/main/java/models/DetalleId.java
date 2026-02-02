package models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetalleId implements Serializable {

    @Column(name = "pedido_id")
    private Integer pedidoId;
    @Column(name = "producto_id")
    private Integer productoId;

    public DetalleId(Integer pedidoId, Integer productoId) {
        this.pedidoId = pedidoId;
        this.productoId = productoId;
    }

    public DetalleId() {
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleId that)) return false;
        return Objects.equals(pedidoId, that.pedidoId) &&
                Objects.equals(productoId, that.productoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, productoId);
    }

}
