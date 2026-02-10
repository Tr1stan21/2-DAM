package models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Producto implements Serializable {
    @Id
    private Integer id;

    @Column(name = "producto", nullable = false, length = 40, unique = true)
    private String producto;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    Categoria categoria;

    @Column(name = "cantidad_por_unidad", length = 20)
    private String cantidadPorUnidad;

    @Column(name = "precio_unidad")
    private BigDecimal precioUnidad;

    @Column(name = "unidades_existencia")
    private Short unidadesExistencia;

    @Column(name = "unidades_pedido")
    private Short unidadesPedido;

    @Column(name = "nivel_nuevo_pedido")
    private Integer nivelNuevoPedido;

    @Column(name = "suspendido", nullable = false)
    private boolean suspendido;

    @OneToMany(mappedBy = "producto")
    private Set<Detalle> detalles = new HashSet<>();

    public Producto() {}

    public Producto(Integer id, String producto, Proveedor proveedor, Categoria categoria,
                    String cantidadPorUnidad, BigDecimal precioUnidad, Short unidadesExistencia,
                    Short unidadesPedido, Integer nivelNuevoPedido, Boolean suspendido) {
        this.id = id;
        this.producto = producto;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.cantidadPorUnidad = cantidadPorUnidad;
        this.precioUnidad = precioUnidad;
        this.unidadesExistencia = unidadesExistencia;
        this.unidadesPedido = unidadesPedido;
        this.nivelNuevoPedido = nivelNuevoPedido;
        this.suspendido = suspendido;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public String getCantidadPorUnidad() { return cantidadPorUnidad; }
    public void setCantidadPorUnidad(String cantidadPorUnidad) { this.cantidadPorUnidad = cantidadPorUnidad; }

    public BigDecimal getPrecioUnidad() { return precioUnidad; }
    public void setPrecioUnidad(BigDecimal precioUnidad) { this.precioUnidad = precioUnidad; }

    public Short getUnidadesExistencia() { return unidadesExistencia; }
    public void setUnidadesExistencia(Short unidadesExistencia) { this.unidadesExistencia = unidadesExistencia; }

    public Short getUnidadesPedido() { return unidadesPedido; }
    public void setUnidadesPedido(Short unidadesPedido) { this.unidadesPedido = unidadesPedido; }

    public Integer getNivelNuevoPedido() { return nivelNuevoPedido; }
    public void setNivelNuevoPedido(Integer nivelNuevoPedido) { this.nivelNuevoPedido = nivelNuevoPedido; }

    public Boolean getSuspendido() { return suspendido; }
    public void setSuspendido(Boolean suspendido) { this.suspendido = suspendido; }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", producto='" + producto + '\'' +
                ", proveedor=" + proveedor +
                ", categoria=" + categoria +
                ", cantidadPorUnidad='" + cantidadPorUnidad + '\'' +
                ", precioUnidad=" + precioUnidad +
                ", unidadesExistencia=" + unidadesExistencia +
                ", unidadesPedido=" + unidadesPedido +
                ", nivelNuevoPedido=" + nivelNuevoPedido +
                ", suspendido=" + suspendido +
                '}';
    }
}