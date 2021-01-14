package db.inicial.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import db.inicial.model.Alumno;
import db.inicial.model.Inscripcion;

public class InscripcionDAO {
	public static int insert(Inscripcion inscripcion, Connection connection) throws SQLException {

		PreparedStatement prepstmt = connection
				.prepareStatement("INSERT INTO INSCRIPCION (id_alumno, id_curso, estado) VALUES(?)");
		prepstmt.setInt(1, inscripcion.getEstudiante().getIdEstudiante());
		prepstmt.setInt(2, inscripcion.getCurso().getIdCurso());

		prepstmt.setString(3, inscripcion.getEstado());
		return prepstmt.executeUpdate();
	}

	public static List<Inscripcion> findAll(Connection connection) throws SQLException {
		List<Inscripcion> listaInscripcion = new ArrayList<Inscripcion>();
		String sql = "SELECT * FROM INSCRIPCION ORDER BY id";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		Inscripcion inscripcion = null;

		while (rs.next()) {
			int idInscripcion = rs.getInt(1);

			int idEstudiante = rs.getInt(2);
			int idProfesor = rs.getInt(3);
			int idCurso = rs.getInt(4);
			String comisionCurso = rs.getNString(5);
			int notaParcial = rs.getInt(6);
			int notaFinal = rs.getInt(7);
			String estado = rs.getNString(8);
			inscripcion = new Inscripcion(idInscripcion, idEstudiante, idProfesor, idCurso, comisionCurso, notaParcial,
					notaFinal, estado);
			inscripcion.setIdEstudiante(idInscripcion);
			listaInscripcion.add(inscripcion);
		}
		return listaInscripcion;
	}

	public static int delete(int idInscripcion, Connection connection) throws SQLException {

		PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM INSCRIPCION WHERE IDINSCRIPCION = ?");
		prepStmt.setInt(1, idInscripcion);

		return prepStmt.executeUpdate();
	}

	public static Inscripcion finById(int id, Connection connection) throws SQLException {

		String sql = "SELECT * FROM INSCRIPCION WHERE ID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		Inscripcion inscripcion = null;
		if (rs.next()) {
			inscripcion = new Inscripcion(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getNString(5),
					rs.getInt(6), rs.getInt(7), rs.getNString(8));
		}

		return inscripcion;
	}
}