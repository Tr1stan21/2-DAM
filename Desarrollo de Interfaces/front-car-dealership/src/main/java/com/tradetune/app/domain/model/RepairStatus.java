package com.tradetune.app.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "repair_status")
public class RepairStatus {
    @Id
    @Column(name = "id_repair_status", nullable = false)
    private Short id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}