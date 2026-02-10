package domain.model;

import jakarta.persistence.*;
import java.util.*;


@Entity
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String ubicacion;

    @Column(name = "aforo_maximo", nullable = false)
    private int aforoMaximo;

    @OneToMany(mappedBy = "aula")
    private List<Clase> clases = new ArrayList<>();

    public Aula() {}

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", ubicacion='" + ubicacion + '\'' +
                ", aforoMaximo=" + aforoMaximo +
                '}';
    }

    public Aula(Long id, String ubicacion, int aforoMaximo) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.aforoMaximo = aforoMaximo;
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

    public int getAforoMaximo() {
        return aforoMaximo;
    }

    public void setAforoMaximo(int aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }
}
