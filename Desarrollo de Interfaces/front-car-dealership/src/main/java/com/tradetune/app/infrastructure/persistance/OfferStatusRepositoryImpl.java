package com.tradetune.app.infrastructure.persistance;

import com.tradetune.app.domain.model.OfferStatus;
import com.tradetune.app.domain.repository.OfferStatusRepository;
import org.hibernate.Session;

public class OfferStatusRepositoryImpl extends CommonRepositoryImpl<OfferStatus, Integer> implements OfferStatusRepository {
    protected OfferStatusRepositoryImpl(Session session) {
        super(session);
    }
}