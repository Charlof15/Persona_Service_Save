package com.ffm.persona.repositorio;

import java.sql.*;
import java.util.*;

import com.ffm.persona.model.OutputConsultaPersonas;
import com.ffm.persona.model.Permisos;
import com.ffm.persona.model.Persona;


public class ConectividadMySql implements ICrud {

	Connection conexion = null;
	public String JDBC_URL = "jdbc:mysql://localhost:3306/db_personas?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	public String JDBC_USER = "root";
	public String JDBC_PASSWORD = "admin";

	public Connection obtenerconexion() {
		try {
			conexion = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			if (conexion != null) {
				System.out.println("Conexi�n establecida");
			}
		} catch (Exception e) {
			System.out.println("Conexion fallida");
		}
		return conexion;
	}
	// 1-. Abre conexion
	// 2.- Perarar sentencia con Preparedstatement
	// 3.- Ejecuta SQL
	// 4.- Cierre de conexion

	public void cerrarConexion(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
			System.err.println("Conexion cerrada");
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
	}

	public Integer insercion(String sqlInsert) {
		Connection conn = this.obtenerconexion();
		PreparedStatement stmt = null;
		Integer rs2 = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			System.out.println(sqlInsert);
			stmt.executeUpdate();
			System.out.println(sqlInsert);
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				rs2 = rs.getInt(1);	
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally { // Funciona para ejecutar si o si un codigo a pesar de que exista un error
			this.cerrarConexion(conn, stmt, null);
		}
		return rs2;
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
		} finally {
			this.cerrarConexion(conn, stmt, null);
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
		} finally {
			this.cerrarConexion(conn, stmt, null);
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
			while (rs.next()) {
				mapRes = new LinkedHashMap<>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					mapRes.put(rsmd.getColumnLabel(i), rs.getString(i));
				}
				lista.add(mapRes);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		} finally {
			this.cerrarConexion(conn, stmt, null);
		}
		return lista;
	}
	
	
	
}
