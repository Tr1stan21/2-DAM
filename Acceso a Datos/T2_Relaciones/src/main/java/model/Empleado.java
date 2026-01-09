package model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Empleado {

    @Id
    @Column(name="dni_empleado")
    private String dniEmpleado;
    @Column
    private String nombre;
    @Column
    private String domicilio;
    @Column
    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private final Set<RestEmpleado> restEmpleados = new HashSet<>();

    public Empleado() {
    }

    public Empleado(String dniEmpleado, String nombre, String domicilio, LocalDate fechaNacimiento) {
        this.dniEmpleado = dniEmpleado;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}

