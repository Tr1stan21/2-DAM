package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RestEmpleadoId implements Serializable {

    @Column(name = "cod_rest", length = 4)
    private String codRest;

    @Column(name = "dni_empleado", length = 9)
    private String dniEmpleado;

    public RestEmpleadoId() {
    }

    public RestEmpleadoId(String codRest, String dniEmpleado) {
        this.codRest = codRest;
        this.dniEmpleado = dniEmpleado;
    }

    public String getCodRest() {
        return codRest;
    }

    public void setCodRest(String codRest) {
        this.codRest = codRest;
    }

    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestEmpleadoId other)) return false;
        return codRest.equals(other.codRest)
                && dniEmpleado.equals(other.dniEmpleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codRest, dniEmpleado);
    }
}
