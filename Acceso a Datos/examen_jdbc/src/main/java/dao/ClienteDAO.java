package dao;

import model.Cliente;
import java.util.List;

public interface ClienteDAO {
    int insert(Cliente c) throws Exception;
    boolean update(Cliente c) throws Exception;
    boolean delete(int id) throws Exception;
    Cliente findById(int id) throws Exception;
    List<Cliente> findAll() throws Exception;
}
