package com.tradetune.app.infrastructure.persistance;

import com.tradetune.app.domain.model.Material;
import com.tradetune.app.domain.repository.MaterialRepository;
import org.hibernate.Session;

public class MaterialRepositoryImpl extends CommonRepositoryImpl<Material, Integer> implements MaterialRepository {
    protected MaterialRepositoryImpl(Session session) {
        super(session);
    }
}