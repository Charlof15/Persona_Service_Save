package com.ffm.persona.model;
import java.util.List;



public class Persona {

	String idpersona;
	String nombre;
	String apellido;
	String email;
	String telefono;
	List<Permisos> permisos;
	
	public Persona() {
		
	}
	
	

	public Persona(String idpersona, String nombre, String apellido, String email, String telefono,
			List<Permisos> permisos) {
		super();
		this.idpersona = idpersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.permisos = permisos;
	}



	public String getIdpersona() {
		return idpersona;
	}

	public void setIdpersona(String idpersona) {
		this.idpersona = idpersona;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Permisos> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permisos> permisos) {
		this.permisos = permisos;
	}

	
	
}
