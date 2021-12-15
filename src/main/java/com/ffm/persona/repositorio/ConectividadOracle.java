package com.ffm.persona.repositorio;

import java.sql.*;
import java.util.*;


public class ConectividadOracle implements ICrud{
	
	
	private static final String JDBC_URL = "jdbc:oracle:thin:@10.216.48.101:1521/GIMTPD";
	private static final String JDBC_USER = "FFMPE";
	private static final String JDBC_PASSWORD = "fFm_p3_2018";

	public Connection obtenerconexion() {
		try {
			System.err.println("Dentro de conexion oracle");
			return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);	
			
		} catch (Exception e) {
			System.err.println("Error al crear conexion : " + e.toString());
			return null;
		}
	}

	@Override
	public void cerrarConexion(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
	        if(conn != null) conn.close();
	       System.err.println("Conexion cerrada");		
		} catch (SQLException e) {
		 System.err.println(e.toString());
		}
	}

	@Override
	public Integer insercion(String sqlInsert) {
		Connection conn = this.obtenerconexion();
		  PreparedStatement stmt = null;
		  try {
			  	stmt = conn.prepareStatement(sqlInsert);
		        stmt.executeUpdate();
		        System.out.println(sqlInsert);
		    } catch (SQLException ex) {
		        ex.printStackTrace(System.out);
		    }finally { //Funciona para ejecutar si o si un codigo a pesar de que exista un error
		    	this.cerrarConexion(conn,stmt,null);
		    }
		return 1;
		
	}

	@Override
	public String actualizacion(String sqlUpdate) {
		Connection conn = this.obtenerconexion();
		  PreparedStatement stmt = null;
		  try {
			  	stmt = conn.prepareStatement(sqlUpdate);
		        stmt.executeUpdate();
		    } catch (SQLException ex) {
		        ex.printStackTrace(System.out);
		    }finally { //Funciona para ejecutar si o si un codigo a pesar de que exista un error
		    	this.cerrarConexion(conn,stmt,null);
		    }
		return sqlUpdate;
	}

	@Override
	public String eliminacion(String sqlDelete) {
		Connection conn = this.obtenerconexion();
		  PreparedStatement stmt = null;
		  try {
			  	stmt = conn.prepareStatement(sqlDelete);
		        stmt.executeUpdate();
		        
		    } catch (SQLException ex) {
		        ex.printStackTrace(System.out);
		    }finally { //Funciona para ejecutar si o si un codigo a pesar de que exista un error
		    	this.cerrarConexion(conn,stmt,null);
		    }
		return sqlDelete;

	}

	@Override
	public List<Map<String, String>> busqueda(String sqlSelect) {
		Connection conn = obtenerconexion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Map<String, String>> lista = new ArrayList<>();
		Map<String, String> mapRes = null;
		try {
			  stmt = conn.prepareStatement(sqlSelect); 
			  rs = stmt.executeQuery();
			  ResultSetMetaData rsmd = rs.getMetaData();
			  while(rs.next()) { 
				  mapRes  = new HashMap<>();
				  for (int i = 1; i <= rsmd.getColumnCount(); i++) { 
					  mapRes.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				  }
				  lista.add(mapRes);
			  }
		    } catch (Exception ex) {
		        ex.printStackTrace(System.out);
		    }finally { 
			this.cerrarConexion(conn,stmt,null);
		}
		  
		  
		  return lista;
	
		
	}
	
	
	
	
}
	