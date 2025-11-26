package dao;

import model.Producto;
import java.util.List;

public interface ProductoDAO {
    int insert(Producto p) throws Exception;
    boolean update(Producto p) throws Exception;
    boolean delete(int id) throws Exception;
    Producto findById(int id) throws Exception;
    List<Producto> findAll() throws Exception;
    
    /**
     * Importación masiva de productos desde una lista usando batch de 5 + transacción.
     */
    void importarProductos(List<Producto> productos) throws Exception;
}
