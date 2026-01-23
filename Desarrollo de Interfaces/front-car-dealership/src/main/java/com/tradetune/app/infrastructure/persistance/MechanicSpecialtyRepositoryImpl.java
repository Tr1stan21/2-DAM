package com.tradetune.app.infrastructure.persistance;

import com.tradetune.app.domain.model.MechanicSpecialty;
import com.tradetune.app.domain.repository.MechanicSpecialtyRepository;
import org.hibernate.Session;

public class MechanicSpecialtyRepositoryImpl extends CommonRepositoryImpl<MechanicSpecialty, Integer> implements MechanicSpecialtyRepository {
    protected MechanicSpecialtyRepositoryImpl(Session session) {
        super(session);
    }
}