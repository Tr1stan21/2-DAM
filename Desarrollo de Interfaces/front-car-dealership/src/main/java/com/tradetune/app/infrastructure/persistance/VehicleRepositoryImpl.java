package com.tradetune.app.infrastructure.persistance;

import com.tradetune.app.domain.model.Vehicle;
import com.tradetune.app.domain.repository.VehicleRepository;
import org.hibernate.Session;

public class VehicleRepositoryImpl extends CommonRepositoryImpl<Vehicle, Integer> implements VehicleRepository {
    protected VehicleRepositoryImpl(Session session) {
        super(session);
    }
}