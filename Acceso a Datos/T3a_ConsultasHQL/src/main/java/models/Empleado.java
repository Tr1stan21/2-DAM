package models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "apellidos", nullable = false, length = 20)
    private String apellidos;

    @Column(name = "nombre", nullable = false, length = 10)
    private String nombre;

    @Column(name = "cargo", length = 30)
    private String cargo;

    @Column(name = "tratamiento", length = 25)
    private String tratamiento;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;

    @Column(name = "direccion", length = 60)
    private String direccion;

    @Column(name = "ciudad", length = 15)
    private String ciudad;

    @Column(name = "region", length = 15)
    private String region;

    @Column(name = "cp", length = 10)
    private String cp;

    @Column(name = "pais", length = 15)
    private String pais;

    @Column(name = "telefono_domicilio", length = 24)
    private String telefonoDomicilio;

    @Column(name = "extension", length = 4)
    private String extension;

    @Column(name = "notas")
    private String notas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jefe_id")
    private Empleado jefe;

    @OneToMany(mappedBy = "jefe")
    private Set<Empleado> subordinados;

    public Empleado() {}

    public Empleado(Integer id, String apellidos, String nombre, String cargo, String tratamiento,
                    LocalDate fechaNacimiento, LocalDate fechaContratacion, String direccion,
                    String ciudad, String region, String cp, String pais, String telefonoDomicilio,
                    String extension, String notas, Empleado jefe) {
        this.id = id;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.cargo = cargo;
        this.tratamiento = tratamiento;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaContratacion = fechaContratacion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.region = region;
        this.cp = cp;
        this.pais = pais;
        this.telefonoDomicilio = telefonoDomicilio;
        this.extension = extension;
        this.notas = notas;
        this.jefe = jefe;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getCp() { return cp; }
    public void setCp(String cp) { this.cp = cp; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getTelefonoDomicilio() { return telefonoDomicilio; }
    public void setTelefonoDomicilio(String telefonoDomicilio) { this.telefonoDomicilio = telefonoDomicilio; }

    public String getExtension() { return extension; }
    public void setExtension(String extension) { this.extension = extension; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }


    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", apellidos='" + apellidos + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaContratacion=" + fechaContratacion +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", region='" + region + '\'' +
                ", cp='" + cp + '\'' +
                ", pais='" + pais + '\'' +
                ", telefonoDomicilio='" + telefonoDomicilio + '\'' +
                ", extension='" + extension + '\'' +
                ", notas='" + notas + '\'' +
                '}';
    }
}