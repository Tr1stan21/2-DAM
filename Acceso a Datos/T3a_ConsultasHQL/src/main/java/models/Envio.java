package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "envios")
public class Envio implements Serializable {

    @Id
    private Integer id;

    @Column(length = 40, nullable = false)
    private String empresa;

    @Column(length = 24)
    private String telefono;

    public Envio() {}

    public Envio(Integer id, String empresa, String telefono) {
        this.id = id;
        this.empresa = empresa;
        this.telefono = telefono;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "Envio{" +
                "id=" + id +
                ", empresa='" + empresa + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}