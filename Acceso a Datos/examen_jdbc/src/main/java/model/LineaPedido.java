package model;

public class LineaPedido {
    private int idLinea;
    private int idPedido;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;

    public LineaPedido() {}

    public LineaPedido(int idLinea, int idPedido, int idProducto, int cantidad, double precioUnitario) {
        this.idLinea = idLinea;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "LineaPedido{" +
                "idLinea=" + idLinea +
                ", idPedido=" + idPedido +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                '}';
    }
}
