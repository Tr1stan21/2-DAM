package domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MechanicSpecialtyId implements Serializable {
    private static final long serialVersionUID = -2753275865383127809L;
    @Column(name = "id_mechanic", nullable = false)
    private Integer idMechanic;

    @Column(name = "id_category", nullable = false)
    private Short idCategory;

    public Integer getIdMechanic() {
        return idMechanic;
    }

    public void setIdMechanic(Integer idMechanic) {
        this.idMechanic = idMechanic;
    }

    public Short getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Short idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MechanicSpecialtyId entity = (MechanicSpecialtyId) o;
        return Objects.equals(this.idMechanic, entity.idMechanic) &&
                Objects.equals(this.idCategory, entity.idCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMechanic, idCategory);
    }
}