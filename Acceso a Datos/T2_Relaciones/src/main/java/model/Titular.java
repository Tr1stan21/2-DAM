package model;

public class Titular {

    private String dniTitular;
    private String nombre;
    private String domicilio;
    private String codRest;

    public Titular() {
    }

    public Titular(String dniTitular, String nombre, String domicilio, String codRest) {
        this.dniTitular = dniTitular;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.codRest = codRest;
    }

    public String getDniTitular() {
        return dniTitular;
    }

    public void setDniTitular(String dniTitular) {
        this.dniTitular = dniTitular;
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

    public String getCodRest() {
        return codRest;
    }

    public void setCodRest(String codRest) {
        this.codRest = codRest;
    }
}
