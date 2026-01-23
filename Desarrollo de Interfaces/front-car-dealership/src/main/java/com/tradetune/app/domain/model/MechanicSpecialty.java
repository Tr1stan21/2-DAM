package com.tradetune.app.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "mechanic_specialty")
public class MechanicSpecialty {
    @EmbeddedId
    private MechanicSpecialtyId id;

    @MapsId("idMechanic")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_mechanic", nullable = false)
    private Mechanic idMechanic;

    @MapsId("idCategory")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_category", nullable = false)
    private VehicleCategory idCategory;

    public MechanicSpecialtyId getId() {
        return id;
    }

    public void setId(MechanicSpecialtyId id) {
        this.id = id;
    }

    public Mechanic getIdMechanic() {
        return idMechanic;
    }

    public void setIdMechanic(Mechanic idMechanic) {
        this.idMechanic = idMechanic;
    }

    public VehicleCategory getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(VehicleCategory idCategory) {
        this.idCategory = idCategory;
    }

}