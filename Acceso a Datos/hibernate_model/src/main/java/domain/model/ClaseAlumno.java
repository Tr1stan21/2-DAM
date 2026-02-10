package domain.model;

import jakarta.persistence.*;


@Entity
public class ClaseAlumno {
    @EmbeddedId
    private ClaseAlumnoId id;

    @Column(name = "nota_final")
    private Double notaFinal;

    @ManyToOne
    @MapsId("codigoClase")
    @JoinColumn(name = "codigo_clase")
    private Clase clase;

    @ManyToOne
    @MapsId("nifAlumno")
    @JoinColumn(name = "nif_alumno")
    private Alumno alumno;

    public ClaseAlumno() {}

    public ClaseAlumno(ClaseAlumnoId id, Clase clase, Alumno alumno, double notaFinal) {
        this.id = id;
        this.clase = clase;
        this.alumno = alumno;
        this.notaFinal = notaFinal;
    }

    public ClaseAlumnoId getId() {
        return id;
    }

    public void setId(ClaseAlumnoId id) {
        this.id = id;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    @Override
    public String toString() {
        return "ClaseAlumno{" +
                "id=" + id +
                ", notaFinal=" + notaFinal +
                ", clase=" + clase +
                ", alumno=" + alumno +
                '}';
    }
}
