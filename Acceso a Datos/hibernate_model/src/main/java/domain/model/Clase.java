package domain.model;

import jakarta.persistence.*;

import java.util.*;


@Entity
public class Clase {
    @Id
    @Column(name = "codigo_clase", length = 20)
    private String codigoClase;

    @Column(length = 255, nullable = false)
    private String titulo;

    @Column(name = "duracion_minutos", nullable = false)
    private int duracionMinutos;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "id_aula")
    private Aula aula;

    @OneToMany(mappedBy = "clase")
    private List<ClaseAlumno> alumnos = new ArrayList<>();

    public Clase() {}

    public Clase(String codigoClase, String titulo, int duracionMinutos, Profesor profesor, Aula aula) {
        this.codigoClase = codigoClase;
        this.titulo = titulo;
        this.duracionMinutos = duracionMinutos;
        this.profesor = profesor;
        this.aula = aula;
    }

    public String getCodigoClase() {
        return codigoClase;
    }

    public void setCodigoClase(String codigoClase) {
        this.codigoClase = codigoClase;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    @Override
    public String toString() {
        return "Clase{" +
                "codigoClase='" + codigoClase + '\'' +
                ", titulo='" + titulo + '\'' +
                ", duracionMinutos=" + duracionMinutos +
                ", aula=" + aula +
                ", profesor=" + profesor +
                '}';
    }
}
