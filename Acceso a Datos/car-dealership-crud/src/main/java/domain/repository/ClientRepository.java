package domain.repository;

import domain.model.Client;


/**
 * Repositorio para gestión de clientes.
 * Extiende BaseRepository para operaciones comunes de lectura.
 * Incluye operaciones de escritura (save, update), ya que los clientes
 * SÍ se crean desde la aplicación según especificaciones.
 */
public interface ClientRepository extends BaseRepository<Client, Integer> {

    /**
     * Guarda un nuevo cliente en la base de datos.
     * @param client Cliente a guardar
     * @return Cliente guardado con ID generado
     */
    Client save(Client client);

    /**
     * Actualiza un cliente existente.
     * @param client Cliente a actualizar
     */
    void update(Client client);

}