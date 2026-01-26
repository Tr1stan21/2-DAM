package com.tradetune.app.infrastructure.persistence;

import com.tradetune.app.domain.model.SalesEmployee;
import com.tradetune.app.domain.repository.SalesEmployeeRepository;
import org.hibernate.Session;

public class SalesEmployeeRepositoryImpl extends CommonRepositoryImpl<SalesEmployee, Integer> implements SalesEmployeeRepository {
    protected SalesEmployeeRepositoryImpl(Session session) {
        super(session);
    }
}
