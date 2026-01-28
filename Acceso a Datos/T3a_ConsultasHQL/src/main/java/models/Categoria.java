package models;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {

    @Id
    private Integer id;
    @Column (unique = true, nullable = false, length = 15)
    private String categoria;
    @Column
    private String descripcion;

    public Categoria() {}

    public Categoria(Integer id, String categoria, String descripcion) {
        this.id = id;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", categoria='" + categoria + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}