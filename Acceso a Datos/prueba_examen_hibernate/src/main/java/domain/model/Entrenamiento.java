package domain.model;

import jakarta.persistence.*;

import java.util.*;


@Entity
public class Entrenamiento {
    @Id
    @Column(name = "codigo_entrenamiento", length = 20)
    private String codigoEntrenamiento;

    @Column(length = 255, nullable = false)
    private String titulo;
    @Column(name = "duracion_horas",nullable = false)
    private int duracionHoras;

    @ManyToOne
    @JoinColumn(name = "id_entrenador")
    private Entrenador entrenador;

    @ManyToOne
    @JoinColumn(name = "id_instalacion")
    private Instalacion instalacion;

    @OneToMany(mappedBy = "entrenamiento")
    private List<EntrenamientoDeportista> deportistas = new ArrayList<>();

    public Entrenamiento() {}

    public Entrenamiento(String codigoEntrenamiento, String titulo, int duracionHoras, Entrenador entrenador, Instalacion instalacion) {
        this.codigoEntrenamiento = codigoEntrenamiento;
        this.titulo = titulo;
        this.duracionHoras = duracionHoras;
        this.entrenador = entrenador;
        this.instalacion = instalacion;
    }

    public String getCodigoEntrenamiento() {
        return codigoEntrenamiento;
    }

    public void setCodigoEntrenamiento(String codigoEntrenamiento) {
        this.codigoEntrenamiento = codigoEntrenamiento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Instalacion getInstalacion() {
        return instalacion;
    }

    public void setInstalacion(Instalacion instalacion) {
        this.instalacion = instalacion;
    }

    @Override
    public String toString() {
        return "Entrenamiento{" +
                "codigoEntrenamiento='" + codigoEntrenamiento + '\'' +
                ", titulo='" + titulo + '\'' +
                ", duracionHoras=" + duracionHoras +
                ", instalacion=" + instalacion +
                ", entrenador=" + entrenador +
                '}';
    }
}

