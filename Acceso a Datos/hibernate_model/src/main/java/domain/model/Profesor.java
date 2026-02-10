package domain.model;

import jakarta.persistence.*;
import java.util.*;


@Entity
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(name = "instrumento_principal", length = 100, nullable = false)
    private String instrumentoPrincipal;

    @OneToMany(mappedBy = "profesor")
    private List<Clase> clases = new ArrayList<>();

public Profesor() {}

    public Profesor(Long id, String nombre, String instrumentoPrincipal) {
        this.id = id;
        this.nombre = nombre;
        this.instrumentoPrincipal = instrumentoPrincipal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstrumentoPrincipal() {
        return instrumentoPrincipal;
    }

    public void setInstrumentoPrincipal(String instrumentoPrincipal) {
        this.instrumentoPrincipal = instrumentoPrincipal;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", instrumentoPrincipal='" + instrumentoPrincipal + '\'' +
                '}';
    }
}
