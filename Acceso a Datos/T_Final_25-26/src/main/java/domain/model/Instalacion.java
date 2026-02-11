package domain.model;

import jakarta.persistence.*;
import java.util.*;


@Entity
public class Instalacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String ubicacion;

    @Column(name = "capacidad_maxima", nullable = false)
    private int capacidadMaxima;

    @OneToMany(mappedBy = "instalacion")
    private List<Entrenamiento> entrenamientos = new ArrayList<>();

    public Instalacion() {}

    public Instalacion(Long id, String ubicacion, int capacidadMaxima) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.capacidadMaxima = capacidadMaxima;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    @Override
    public String toString() {
        return "Instalacion{" +
                "id=" + id +
                ", ubicacion='" + ubicacion + '\'' +
                ", capacidadMaxima=" + capacidadMaxima +
                '}';
    }
}
