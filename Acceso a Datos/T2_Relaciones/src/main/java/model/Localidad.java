package model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Localidad {

    @Id
    private int codLocalidad;
    @Column
    private String nombre;

    @OneToMany(mappedBy = "localidad", cascade = CascadeType.ALL)
    private final Set<Restaurante> restaurantes = new HashSet<>();

    public Localidad() {
    }

    public Localidad(int codLocalidad, String nombre) {
        this.codLocalidad = codLocalidad;
        this.nombre = nombre;
    }

    public int getCodLocalidad() {
        return codLocalidad;
    }

    public void setCodLocalidad(int codLocalidad) {
        this.codLocalidad = codLocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addRestaurante(Restaurante r) {
        restaurantes.add(r);
        r.setLocalidad(this);
    }
}
