package com.tradetune.app.infrastructure.persistance;

import com.tradetune.app.domain.model.Worker;
import com.tradetune.app.domain.repository.WorkerRepository;
import org.hibernate.Session;

public class WorkerRepositoryImpl extends CommonRepositoryImpl<Worker, Integer> implements WorkerRepository {
    protected WorkerRepositoryImpl(Session session) {
        super(session);
    }
}