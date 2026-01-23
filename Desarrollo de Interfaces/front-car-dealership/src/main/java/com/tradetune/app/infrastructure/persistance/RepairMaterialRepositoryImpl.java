package com.tradetune.app.infrastructure.persistance;

import com.tradetune.app.domain.model.RepairMaterial;
import com.tradetune.app.domain.model.RepairMaterialId;
import com.tradetune.app.domain.repository.RepairMaterialRepository;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class RepairMaterialRepositoryImpl implements RepairMaterialRepository {
    protected RepairMaterialRepositoryImpl(Session session) {

    }

    @Override
    public RepairMaterial save(RepairMaterial entity) {
        return null;
    }

    @Override
    public Optional<RepairMaterial> findById(RepairMaterialId repairMaterialId) {
        return Optional.empty();
    }

    @Override
    public List<RepairMaterial> findAll() {
        return List.of();
    }

    @Override
    public RepairMaterial update(RepairMaterial entity) {
        return null;
    }

    @Override
    public void delete(RepairMaterial entity) {

    }

    @Override
    public boolean deleteById(RepairMaterialId repairMaterialId) {
        return false;
    }
}