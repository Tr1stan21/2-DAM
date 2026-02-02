package models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Pedido implements Serializable {

    @Id
    private Integer id;
    private Integer clienteId;
    private Integer empleadoId;
    private LocalDate fechaPedido;
    private LocalDate fechaEntrega;
    private LocalDate fechaEnvio;
    private Integer envioId;
    private BigDecimal cargo;
    private String destinatario;
    private String direccionDestinatario;
    private String ciudadDestinatario;
    private String regionDestinatario;
    private String cpDestinatario;
    private String paisDestinatario;

    @OneToMany(mappedBy = "pedido")
    Set<Detalle> detalles = new HashSet<>();

    public Pedido() {}

    public Pedido(Integer id, Integer clienteId, Integer empleadoId, LocalDate fechaPedido,
                  LocalDate fechaEntrega, LocalDate fechaEnvio, Integer envioId, BigDecimal cargo,
                  String destinatario, String direccionDestinatario, String ciudadDestinatario,
                  String regionDestinatario, String cpDestinatario, String paisDestinatario) {
        this.id = id;
        this.clienteId = clienteId;
        this.empleadoId = empleadoId;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
        this.fechaEnvio = fechaEnvio;
        this.envioId = envioId;
        this.cargo = cargo;
        this.destinatario = destinatario;
        this.direccionDestinatario = direccionDestinatario;
        this.ciudadDestinatario = ciudadDestinatario;
        this.regionDestinatario = regionDestinatario;
        this.cpDestinatario = cpDestinatario;
        this.paisDestinatario = paisDestinatario;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }

    public Integer getEmpleadoId() { return empleadoId; }
    public void setEmpleadoId(Integer empleadoId) { this.empleadoId = empleadoId; }

    public LocalDate getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(LocalDate fechaPedido) { this.fechaPedido = fechaPedido; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public LocalDate getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDate fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public Integer getEnvioId() { return envioId; }
    public void setEnvioId(Integer envioId) { this.envioId = envioId; }

    public BigDecimal getCargo() { return cargo; }
    public void setCargo(BigDecimal cargo) { this.cargo = cargo; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getDireccionDestinatario() { return direccionDestinatario; }
    public void setDireccionDestinatario(String direccionDestinatario) { this.direccionDestinatario = direccionDestinatario; }

    public String getCiudadDestinatario() { return ciudadDestinatario; }
    public void setCiudadDestinatario(String ciudadDestinatario) { this.ciudadDestinatario = ciudadDestinatario; }

    public String getRegionDestinatario() { return regionDestinatario; }
    public void setRegionDestinatario(String regionDestinatario) { this.regionDestinatario = regionDestinatario; }

    public String getCpDestinatario() { return cpDestinatario; }
    public void setCpDestinatario(String cpDestinatario) { this.cpDestinatario = cpDestinatario; }

    public String getPaisDestinatario() { return paisDestinatario; }
    public void setPaisDestinatario(String paisDestinatario) { this.paisDestinatario = paisDestinatario; }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", empleadoId=" + empleadoId +
                ", fechaPedido=" + fechaPedido +
                ", fechaEntrega=" + fechaEntrega +
                ", fechaEnvio=" + fechaEnvio +
                ", envioId=" + envioId +
                ", cargo=" + cargo +
                ", destinatario='" + destinatario + '\'' +
                ", direccionDestinatario='" + direccionDestinatario + '\'' +
                ", ciudadDestinatario='" + ciudadDestinatario + '\'' +
                ", regionDestinatario='" + regionDestinatario + '\'' +
                ", cpDestinatario='" + cpDestinatario + '\'' +
                ", paisDestinatario='" + paisDestinatario + '\'' +
                '}';
    }
}