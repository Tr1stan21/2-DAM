package domain.model;

import jakarta.persistence.*;


@Entity
public class EntrenamientoDeportista {
    @EmbeddedId
    private EntrenamientoDeportistaId id;

    @Column(name = "rendimiento_final")
    private Double rendimientoFinal;

    @ManyToOne
    @MapsId("codigoEntrenamiento")
    @JoinColumn(name = "codigo_entrenamiento")
    private Entrenamiento entrenamiento;

    @ManyToOne
    @MapsId("dniDeportista")
    @JoinColumn(name = "dni_deportista")
    private Deportista deportista;

    public EntrenamientoDeportista() {}

    public EntrenamientoDeportista(EntrenamientoDeportistaId id, Entrenamiento entrenamiento, Deportista deportista, double rendimientoFinal) {
        this.id = id;
        this.entrenamiento = entrenamiento;
        this.deportista = deportista;
        this.rendimientoFinal = rendimientoFinal;
    }

    public EntrenamientoDeportistaId getId() {
        return id;
    }

    public void setId(EntrenamientoDeportistaId id) {
        this.id = id;
    }

    public Entrenamiento getEntrenamiento() {
        return entrenamiento;
    }

    public void setEntrenamiento(Entrenamiento entrenamiento) {
        this.entrenamiento = entrenamiento;
    }

    public Deportista getDeportista() {
        return deportista;
    }

    public void setDeportista(Deportista deportista) {
        this.deportista = deportista;
    }

    public double getRendimientoFinal() {
        return rendimientoFinal;
    }

    public void setRendimientoFinal(double rendimientoFinal) {
        this.rendimientoFinal = rendimientoFinal;
    }

    @Override
    public String toString() {
        return "EntrenamientoDeportista{" +
                "id=" + id +
                ", entrenamiento=" + entrenamiento +
                ", rendimientoFinal=" + rendimientoFinal +
                ", deportista=" + deportista +
                '}';
    }
}

