package com.tradetune.app.infrastructure.persistance;

import com.tradetune.app.domain.model.Mechanic;
import com.tradetune.app.domain.repository.MechanicRepository;
import org.hibernate.Session;

public class MechanicRepositoryImpl extends CommonRepositoryImpl<Mechanic, Integer> implements MechanicRepository {
    protected MechanicRepositoryImpl(Session session) {
        super(session);
    }
}