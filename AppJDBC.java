package db.inicial;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
//Menu: 1. cursos, 2.: estudiantes, 3: inscripciones
//necesitamos alta, listado, modificacion  y baja de alumno

// y de curso....Inscripcion, cancelacion, listados (varios)
//
import db.inicial.DAO.AdminDB;
import db.inicial.controlator.AlumnoControl;
import db.inicial.controlator.CursoControl;
import db.inicial.controlator.InscripcionControl;

public class AppJDBC {
	private static final int LONGIRTUD_MINIMA = 2;

	public static void main(String[] args) throws LongitudCadenaException {
		try {
			Connection connection = AdminDB.obtenerConexion();
			// Statement stmt = connection.createStatement();// creo una sentencia, contiene
			// la operacion que voy a ejecutar

			Scanner sc = new Scanner(System.in);
			System.out.println("SISTEMA DE CURSOS");
			System.out.println();
			int opcion = menuOpciones(sc);
			while (opcion != 0) {

				switch (opcion) {
				case 1:
					// Curso cursos = new Curso(sc, connection );
					CursoControl.submenuCursos(sc, connection);

					// altaCurso(sc, connection);
					break;

				case 2:
					AlumnoControl.submenuEstudiante(sc, connection);
					break;
				case 3:
					InscripcionControl.submenuInscripcion(sc, connection);
					break;

				}
				opcion = menuOpciones(sc);
			}
			connection.close();

		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
			System.out.println("Error en carga de Clase driver SQL: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Error en ejecucion SQL: " + e.getMessage());
		}

	}

	private static int menuOpciones(Scanner sc) {
		System.out.println();
		System.out.println("Menu: ");
		System.out.println("1. Curso");
		System.out.println("2. Estudiantes");
		System.out.println("3. Inscripción");
		System.out.println("0.Salir");
		System.out.println();
		System.out.println("Ingrese opcion: ");

		return sc.nextInt();
	}

}
