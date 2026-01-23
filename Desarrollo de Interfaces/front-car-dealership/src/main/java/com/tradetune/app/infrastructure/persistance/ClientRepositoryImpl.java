package com.tradetune.app.infrastructure.persistance;

import com.tradetune.app.domain.model.Client;
import com.tradetune.app.domain.repository.ClientRepository;
import org.hibernate.Session;

public class ClientRepositoryImpl extends CommonRepositoryImpl<Client, Integer> implements ClientRepository {
    /**
     * Constructor que detecta automáticamente el tipo de entidad
     *
     * @param session Sesión de Hibernate
     */
    public ClientRepositoryImpl(Session session) {
        super(session);
    }
}
