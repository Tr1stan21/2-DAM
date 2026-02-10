package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table (name = "clientes")
public class Cliente implements Serializable {
    @Id
    private Integer id;
    @Column (unique = true, nullable = false, length = 5)
    private String codigo;
    @Column (unique = true, nullable = false, length = 40)
    private String empresa;
    @Column (length = 30)
    private String contacto;
    @Column (name = "cargo_contacto", length = 30)
    private String cargoContacto;
    @Column (length = 60)
    private String direccion;
    @Column (length = 15)
    private String ciudad;
    @Column (length = 15)
    private String region;
    @Column (length = 10)
    private String cp;
    @Column (length = 15)
    private String pais;
    @Column (length = 24)
    private String telefono;
    @Column (length = 24)
    private String fax;

    public Cliente() {}

    public Cliente(Integer id, String codigo, String empresa, String contacto, String cargoContacto,
                   String direccion, String ciudad, String region, String cp, String pais,
                   String telefono, String fax) {
        this.id = id;
        this.codigo = codigo;
        this.empresa = empresa;
        this.contacto = contacto;
        this.cargoContacto = cargoContacto;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.region = region;
        this.cp = cp;
        this.pais = pais;
        this.telefono = telefono;
        this.fax = fax;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }

    public String getCargoContacto() { return cargoContacto; }
    public void setCargoContacto(String cargoContacto) { this.cargoContacto = cargoContacto; }

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

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax; }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", empresa='" + empresa + '\'' +
                ", contacto='" + contacto + '\'' +
                ", cargoContacto='" + cargoContacto + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", region='" + region + '\'' +
                ", cp='" + cp + '\'' +
                ", pais='" + pais + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fax='" + fax + '\'' +
                '}';
    }
}