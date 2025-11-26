package dao;

import model.LineaPedido;
import java.util.List;

public interface LineaPedidoDAO {
    int insert(LineaPedido lp) throws Exception;
    List<LineaPedido> findByPedido(int idPedido) throws Exception;
}
