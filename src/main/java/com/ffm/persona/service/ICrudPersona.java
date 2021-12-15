package com.ffm.persona.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ffm.persona.model.Permisos;
import com.ffm.persona.model.Persona;

//(delete,insert,update,select)
public interface ICrudPersona {

	public Object select();
	public String update(Persona persona, Integer idPersona);
	public Object delete(Integer idPersona);
	//public List<Persona> select1(Persona persona);
	public void insert(Persona persona);



	
	
}
