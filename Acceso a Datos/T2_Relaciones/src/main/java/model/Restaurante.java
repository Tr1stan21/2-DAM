package model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restaurante {

    @Id
    private String codRest;
    @Column
    private String nombre;
    @Column
    private String licenciaFiscal;
    @Column
    private String domicilio;
    @Column
    private LocalDate fechaApertura;
    @Column
    private String horario;

    @ManyToOne
    @JoinColumn(name = "cod_localidad")
    private Localidad localidad;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private final Set<RestEmpleado> restEmpleados = new HashSet<>();

    public Restaurante() {
    }

    public Restaurante(String codRest, String nombre, String licenciaFiscal,
                       String domicilio, LocalDate fechaApertura,
                       String horario) {
        this.codRest = codRest;
        this.nombre = nombre;
        this.licenciaFiscal = licenciaFiscal;
        this.domicilio = domicilio;
        this.fechaApertura = fechaApertura;
        this.horario = horario;
    }

    public String getCodRest() {
        return codRest;
    }

    public void setCodRest(String codRest) {
        this.codRest = codRest;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLicenciaFiscal() {
        return licenciaFiscal;
    }

    public void setLicenciaFiscal(String licenciaFiscal) {
        this.licenciaFiscal = licenciaFiscal;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurante other)) return false;
        return codRest != null && codRest.equals(other.codRest);
    }

    @Override
    public int hashCode() {
        return codRest != null ? codRest.hashCode() : 0;
    }

}

