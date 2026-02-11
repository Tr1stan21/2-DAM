package domain.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Deportista {
    @Id
    @Column(length = 10)
    private String dni;

    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    private String apellido;

    @OneToMany(mappedBy = "deportista")
    private List<EntrenamientoDeportista> entrenamientos = new ArrayList<>();

    public Deportista() {}

    public Deportista(String dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Deportista{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
