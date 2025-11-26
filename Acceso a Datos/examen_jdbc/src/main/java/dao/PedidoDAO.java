package dao;

import model.Pedido;
import model.Producto;
import model.Cliente;
import model.LineaPedido;
import java.util.List;

public interface PedidoDAO {
    int insert(Pedido p) throws Exception;
    Pedido findById(int id) throws Exception;
    List<Pedido> findAll() throws Exception;

    
    /**
     * Devuelve las líneas de un pedido.
     */    
    List<LineaPedido> findLineasByPedido(int idPedido) throws Exception;
    
    /**
     * Crea un pedido completo (cabecera + líneas) en una sola transacción.
     */
    void crearPedidoCompleto(Cliente c, List<LineaPedido> lineas) throws Exception;
}
