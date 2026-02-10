package domain.model;

import jakarta.persistence.*;
import java.util.*;


@Entity
public class Alumno {
    @Id
    @Column(length = 12)
    private String nif;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 120, nullable = false)
    private String apellidos;

    @OneToMany(mappedBy = "alumno")
    private List<ClaseAlumno> clases = new ArrayList<>();

    public Alumno() {}

    public Alumno(String nif, String nombre, String apellidos) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nif='" + nif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}
