package com.ffm.persona.model;

public class Permisos{

	String id;
	String descripcion;
	String fecha;
	String estado;
	String idpersona;
	String prueba;
	
	
	public Permisos() {
	}

	public Permisos(String id, String descripcion, String fecha, String estado, String idpersona) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.estado = estado;
		this.idpersona = idpersona;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIdpersona() {
		return idpersona;
	}

	public void setIdpersona(String idpersona) {
		this.idpersona = idpersona;
	}
	
}
	