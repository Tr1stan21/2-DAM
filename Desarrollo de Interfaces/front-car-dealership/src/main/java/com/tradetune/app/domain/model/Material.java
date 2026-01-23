package com.tradetune.app.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_material_type", nullable = false)
    private MaterialType idMaterialType;

    @Column(name = "name", nullable = false, length = 160)
    private String name;

    @Column(name = "price_ref", nullable = false, precision = 12, scale = 2)
    private BigDecimal priceRef;

    @ColumnDefault("1")
    @Column(name = "active", nullable = false)
    private Byte active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MaterialType getIdMaterialType() {
        return idMaterialType;
    }

    public void setIdMaterialType(MaterialType idMaterialType) {
        this.idMaterialType = idMaterialType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPriceRef() {
        return priceRef;
    }

    public void setPriceRef(BigDecimal priceRef) {
        this.priceRef = priceRef;
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

}