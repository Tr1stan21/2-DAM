package com.tradetune.app.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "repair_material")
public class RepairMaterial {
    @EmbeddedId
    private RepairMaterialId id;

    @MapsId("idRepair")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_repair", nullable = false)
    private Repair idRepair;

    @MapsId("idMaterial")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_material", nullable = false)
    private Material idMaterial;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price_applied", nullable = false, precision = 12, scale = 2)
    private BigDecimal unitPriceApplied;

    public RepairMaterialId getId() {
        return id;
    }

    public void setId(RepairMaterialId id) {
        this.id = id;
    }

    public Repair getIdRepair() {
        return idRepair;
    }

    public void setIdRepair(Repair idRepair) {
        this.idRepair = idRepair;
    }

    public Material getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Material idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPriceApplied() {
        return unitPriceApplied;
    }

    public void setUnitPriceApplied(BigDecimal unitPriceApplied) {
        this.unitPriceApplied = unitPriceApplied;
    }

}