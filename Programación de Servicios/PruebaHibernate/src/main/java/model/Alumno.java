package model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Alumno implements Serializable {
	// Comentario mas grande
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAlumno;
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column
	private int edad;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@PrimaryKeyJoinColumn
//	private Matricula matricula; 
	

	public Alumno() {
		super();
	}

	
	public Alumno(String nombre, String apellido, int edad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}

	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int num) {
		this.idAlumno = num;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
//	public Matricula getMatricula() {
//		return matricula;
//	}
//
//
//	public void setMatricula(Matricula matricula) {
//		this.matricula = matricula;
//	}


	
}
