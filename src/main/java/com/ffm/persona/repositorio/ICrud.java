package com.ffm.persona.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ICrud {
	public Connection obtenerconexion();
	public void cerrarConexion(Connection conn, PreparedStatement stmt, ResultSet rs);
	public Integer insercion(String sqlInsert);
	public String actualizacion(String sqlUpdate);
	public String eliminacion(String sqlDelete);
	public List<Map<String, String>> busqueda(String sqlSelect);

	
}

