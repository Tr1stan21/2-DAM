package dao;

import model.Categoria;
import java.util.List;

public interface CategoriaDAO {
    int insert(Categoria c) throws Exception;  // devuelve id generado
    boolean update(Categoria c) throws Exception;
    boolean delete(int id) throws Exception;
    Categoria findById(int id) throws Exception;
    List<Categoria> findAll() throws Exception;
}
