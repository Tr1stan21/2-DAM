package domain.model;

import jakarta.persistence.*;
import java.util.*;
import java.io.Serializable;


@Embeddable
public class ClaseAlumnoId implements Serializable {
    @Column(name = "codigo_clase", length = 20)
    private String codigoClase;

    @Column(name = "nif_alumno", length = 12)
    private String nifAlumno;

    public ClaseAlumnoId() {}

    public ClaseAlumnoId(String codigoClase, String nifAlumno) {
        this.codigoClase = codigoClase;
        this.nifAlumno = nifAlumno;
    }

    public String getCodigoClase() {
        return codigoClase;
    }

    public void setCodigoClase(String codigoClase) {
        this.codigoClase = codigoClase;
    }

    public String getNifAlumno() {
        return nifAlumno;
    }

    public void setNifAlumno(String nifAlumno) {
        this.nifAlumno = nifAlumno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClaseAlumnoId that)) return false;
            return Objects.equals(codigoClase, that.codigoClase) &&
               Objects.equals(nifAlumno, that.nifAlumno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoClase, nifAlumno);
    }

    @Override
    public String toString() {
        return "ClaseAlumnoId{" +
                "codigoClase='" + codigoClase + '\'' +
                ", nifAlumno='" + nifAlumno + '\'' +
                '}';
    }
}
