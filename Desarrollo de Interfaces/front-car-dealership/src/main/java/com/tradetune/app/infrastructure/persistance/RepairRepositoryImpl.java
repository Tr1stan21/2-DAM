package com.tradetune.app.infrastructure.persistance;

import com.tradetune.app.domain.model.Repair;
import com.tradetune.app.domain.repository.RepairRepository;
import org.hibernate.Session;

public class RepairRepositoryImpl extends CommonRepositoryImpl<Repair, Integer> implements RepairRepository {
    protected RepairRepositoryImpl(Session session) {
        super(session);
    }
}