package model;

import jakarta.persistence.*;

@Entity
public class RestEmpleado {

    @EmbeddedId
    private RestEmpleadoId id;

    @ManyToOne
    @MapsId("codRest")
    @JoinColumn(name = "cod_rest")
    private Restaurante restaurante;

    @ManyToOne
    @MapsId("dniEmpleado")
    @JoinColumn(name = "dni_empleado")
    private Empleado empleado;

    @Column
    private String funcion;

    public RestEmpleado(String funcion, Empleado empleado, Restaurante restaurante) {
        this.funcion = funcion;
        this.empleado = empleado;
        this.restaurante = restaurante;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public RestEmpleado() {
    }

    public RestEmpleadoId getId() {
        return id;
    }

    public void setId(RestEmpleadoId id) {
        this.id = id;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }
}

