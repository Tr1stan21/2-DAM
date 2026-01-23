package com.tradetune.app.infrastructure.persistance;

import com.tradetune.app.domain.model.HeadMechanic;
import com.tradetune.app.domain.repository.HeadMechanicRepository;
import org.hibernate.Session;

public class HeadMechanicRepositoryImpl extends CommonRepositoryImpl<HeadMechanic, Integer> implements HeadMechanicRepository {
    protected HeadMechanicRepositoryImpl(Session session) {
        super(session);
    }
}