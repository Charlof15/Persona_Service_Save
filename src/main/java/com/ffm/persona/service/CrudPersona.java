package com.ffm.persona.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ffm.persona.model.OutputConsultaPersonas;
import com.ffm.persona.model.OutputEliminaPersona;
import com.ffm.persona.model.Permisos;
import com.ffm.persona.model.Persona;
import com.ffm.persona.repositorio.ConectividadMySql;
import com.ffm.persona.repositorio.ConectividadOracle;
import com.ffm.persona.repositorio.ICrud;
import com.mysql.cj.protocol.Resultset;

public class CrudPersona implements ICrudPersona{
	
	ICrud crud = new ConectividadMySql();
	OutputConsultaPersonas outputWs = new OutputConsultaPersonas();
	
	  @Override
	   public Object select()  {
		 System.out.print("");
	     String select = "SELECT * FROM personas";
	     String select2 = "SELECT * FROM permisos";
	     List<Map<String, String>> rs = crud.busqueda(select);
	     List<Map<String, String>> rs2 = crud.busqueda(select2);
	     List<Persona> listapersona = new ArrayList<>();
	    
	     try {
	       for (Map<String, String> map : rs) {
	    	   List<Permisos> listapermisos = new LinkedList<>();
	    	   Persona p = new Persona();
	    
		       p.setIdpersona(map.get("idPersona"));
		       p.setNombre(map.get("apellido"));
		       p.setApellido(map.get("apellido"));
		       p.setEmail(map.get("email"));
		       p.setTelefono(map.get("telefono"));
	       for(Map<String, String> map2 : rs2) {
	    	   if (p.getIdpersona().equals(map2.get("idpersona"))) {
	    		   Permisos per = new Permisos();
	    		   per.setId(map2.get("id"));
	    		   per.setDescripcion(map2.get("descripcion"));
	    		   per.setFecha(map2.get("fecha"));
	    		   per.setEstado(map2.get("estado"));
	    		   listapermisos.add(per);
	    	   }
	    	   
	         }
	       p.setPermisos(listapermisos);
	       listapersona.add(p);
	       }
	       
	      
	       
	      
	       
	       outputWs.setMensaje("Operacion correcta");
	       outputWs.setDescripcion("Lista de personas");
	       outputWs.setPersonas(listapersona);
	  } catch (Exception e) {
	       System.err.println("Error :" + e.toString());
	        } 
	     
	     return outputWs;	
	   }

	 
	 public void insert(Persona persona)  {
		String insert  = "INSERT INTO personas (nombre,apellido,email,telefono) VALUES ('"+ persona.getNombre() + "', '" + persona.getApellido() + "' , '" + persona.getEmail() + " ', '" + persona.getTelefono() + "')";
		Integer idpersona = crud.insercion(insert);
		System.out.println("Se inserto correctamente la persona");
		List<Permisos> listaPermisos = persona.getPermisos();
		for(Permisos ls : listaPermisos) {
			ls.getDescripcion();
			ls.getFecha();
			ls.getEstado();	
		String insert1 = "INSERT INTO permisos (descripcion,fecha,estado,idpersona) VALUES ('"+ ls.getDescripcion() +"', '" + ls.getFecha() + "', '"+ ls.getEstado()+ "', "+ idpersona +")";
		crud.insercion(insert1);
		System.out.println("Se insertaron correctamente los permisos");
		}	
		}
	
	@Override
	public String update(Persona persona, Integer idPersona ) {
		String update = "UPDATE personas SET telefono = '"+ persona.getTelefono() +"', apellido = '"+ persona.getApellido() +"' ,nombre ='"+ persona.getNombre() +"',email ='"+ persona.getEmail() +"' WHERE idPersona= "+ idPersona +"";
		System.err.println(update);
		String ll = crud.actualizacion(update);
		return ll;
	}

	@Override
	public Object delete(Integer idPersona) {
		OutputEliminaPersona outputWs = new OutputEliminaPersona();
		String delete = "DELETE FROM personas WHERE idPersona = "+ idPersona +"";
		System.err.println(delete);
		outputWs.setMensaje("Ejecucion correcta");
		outputWs.setDescripcion("Persona con id :" + idPersona + " eliminada");
		String aa = crud.eliminacion(delete);
		return outputWs;
	}
		
}


	



