package com.tradetune.app.infrastructure.persistance;

import com.tradetune.app.domain.model.Sale;
import com.tradetune.app.domain.repository.SaleRepository;
import org.hibernate.Session;

public class SaleRepositoryImpl extends CommonRepositoryImpl<Sale, Integer> implements SaleRepository {
    protected SaleRepositoryImpl(Session session) {
        super(session);
    }
}