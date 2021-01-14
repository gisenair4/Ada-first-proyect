package db.inicial.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.inicial.model.Alumno;

public class AlumnoDAO {

	public static int insert(Alumno estudiante, Connection connection) throws SQLException {

		PreparedStatement prepstmt = connection.prepareStatement("INSERT INTO ESTUDIANTE (NAME) VALUES(?)");
		prepstmt.setNString(1, estudiante.getNombreEstudiante());
		prepstmt.setNString(2, estudiante.getApellidoEstudiante());

		return prepstmt.executeUpdate();
	}

	public static List<Alumno> findAll(Connection connection) throws SQLException {
		List<Alumno> listaEstudiante = new ArrayList<Alumno>();
		String sql = "SELECT * FROM ALUMNO";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		Alumno estudiante = null;

		while (rs.next()) {
			int idEstudiante = rs.getInt(1);
			String nombreEstudiante = rs.getNString(2);
			String lastnameEstudiante = rs.getNString(3);

			estudiante = new Alumno(idEstudiante, nombreEstudiante, lastnameEstudiante);
			estudiante.setIdEstudiante(idEstudiante);
			listaEstudiante.add(estudiante);
		}

		return listaEstudiante;

	}

	public static int delete(int idEstudiante, Connection connection) throws SQLException {

		PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM ALUMNO WHERE NAME = ?, LASTNAME = ?");
		prepStmt.setInt(1, idEstudiante);

		return prepStmt.executeUpdate();

	}

	public static int update(Alumno estudiante, Connection connection) throws SQLException {

		PreparedStatement prepStmt = connection.prepareStatement("UPDATE ALUMNO SET NAME = ? WHERE ID = ?");
		prepStmt.setInt(1, estudiante.getIdEstudiante());
		prepStmt.setNString(2, estudiante.getNombreEstudiante());
		prepStmt.setNString(3, estudiante.getApellidoEstudiante());

		return prepStmt.executeUpdate();

	}

	public static List<Alumno> findByName(String name, Connection connection) throws SQLException {

		String sql2 = "SELECT * FROM ALUMNO WHERE NAME LIKE '%" + name + "%' ORDER BY NAME";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql2);

		List<Alumno> lista = new ArrayList<Alumno>();
		Alumno estudiante = null;
		while (rs.next()) {
			estudiante = new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3));
			lista.add(estudiante);
		}
		return lista;

	}

	public static Alumno finById(int id, Connection connection) throws SQLException {

		String sql = "SELECT * FROM ALUMNO WHERE ID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		Alumno estudiante = null;
		if (rs.next()) {
			estudiante = new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3));
		}

		return estudiante;

	}

	public static List<Alumno> findBylastName(String lastname, Connection connection) throws SQLException {

		String sql2 = "SELECT * FROM ALUMNO WHERE LASTNAME LIKE '%" + lastname + "%' ORDER BY LASTNAME";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql2);

		List<Alumno> lista = new ArrayList<Alumno>();
		Alumno estudiante = null;
		while (rs.next()) {
			estudiante = new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3));
			lista.add(estudiante);
		}
		return lista;
	}

}
