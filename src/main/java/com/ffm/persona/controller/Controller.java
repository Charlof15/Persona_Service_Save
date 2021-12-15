package com.ffm.persona.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ffm.persona.model.Permisos;
import com.ffm.persona.model.Persona;
import com.ffm.persona.service.CrudPersona;
import com.ffm.persona.service.ICrudPersona;

@RestController
public class Controller {
	
	ICrudPersona crud = new CrudPersona();
	

	@PostMapping(path = "/personas")
	public Object creaPersona(@RequestBody Persona persona){
		try {
			crud.insert(persona);
			return new ResponseEntity<>("Se agrego correctamente el usuario", HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(
				      HttpStatus.NOT_FOUND, "No funciona", e);
		}
					}
	
	@PatchMapping(path = "/personas/idPersona/{idPersona}")
	public Object actualizaPersona(@PathVariable(value = "idPersona") Integer idPersona,@RequestBody Persona persona){
		try {
			crud.update(persona, idPersona);
			return new ResponseEntity<>("Se actualizo correctamente el usuario", HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(
				      HttpStatus.NOT_FOUND, "No funciona", e);
		}	
		
	}
	
	@DeleteMapping(path = "/personas/idPersona/{idPersona}")
	public Object eliminaPersona(@PathVariable(value = "idPersona") Integer idPersona){
		try {
			return new ResponseEntity<>(crud.delete(idPersona), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(
				      HttpStatus.NOT_FOUND, "No funciona", e);
		}
	}
	
	/*@GetMapping(path = "/personas/idPersona/{idPersona}")
	public Object busquedaPersonaPorId(@PathVariable(value = "idPersona") Integer idPersona){
				return crud.select1(idPersona);
	}*/
	
	@GetMapping(path = "/personas")
	public Object buscaTodos(){
		return crud.select();
	}


@GetMapping("/hola")
    ResponseEntity<String> holaMundo() {
	try {
		return new ResponseEntity<>("Hola Mundo desde una respuesta HTTP!", HttpStatus.OK);
	} catch (Exception e) {
		throw new ResponseStatusException(
			      HttpStatus.NOT_FOUND, "No funciona", e);
	
	}
}
}