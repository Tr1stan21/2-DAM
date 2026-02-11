package domain.model;

import jakarta.persistence.*;
import java.util.*;
import java.io.Serializable;


@Embeddable
public class EntrenamientoDeportistaId implements Serializable {
    @Column(name = "codigo_entrenamiento", length = 20)
    private String codigoEntrenamiento;

    @Column(name = "dni_deportista", length = 10)
    private String dniDeportista;

    public EntrenamientoDeportistaId() {}

    public EntrenamientoDeportistaId(String codigoEntrenamiento, String dniDeportista) {
        this.codigoEntrenamiento = codigoEntrenamiento;
        this.dniDeportista = dniDeportista;
    }

    public String getCodigoEntrenamiento() {
        return codigoEntrenamiento;
    }

    public void setCodigoEntrenamiento(String codigoEntrenamiento) {
        this.codigoEntrenamiento = codigoEntrenamiento;
    }

    public String getDniDeportista() {
        return dniDeportista;
    }

    public void setDniDeportista(String dniDeportista) {
        this.dniDeportista = dniDeportista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntrenamientoDeportistaId)) return false;
        EntrenamientoDeportistaId that = (EntrenamientoDeportistaId) o;
        return Objects.equals(codigoEntrenamiento, that.codigoEntrenamiento) &&
               Objects.equals(dniDeportista, that.dniDeportista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoEntrenamiento, dniDeportista);
    }

    @Override
    public String toString() {
        return "EntrenamientoDeportistaId{" +
                "codigoEntrenamiento='" + codigoEntrenamiento + '\'' +
                ", dniDeportista='" + dniDeportista + '\'' +
                '}';
    }
}
