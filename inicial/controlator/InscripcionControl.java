package db.inicial.controlator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import db.inicial.DAO.AlumnoDAO;
import db.inicial.DAO.CursoDAO;
import db.inicial.DAO.InscripcionDAO;
import db.inicial.model.Alumno;
import db.inicial.model.Curso;
import db.inicial.model.Inscripcion;

public class InscripcionControl {

	public static void submenuInscripcion(Scanner sc, Connection connection) throws SQLException {
		System.out.println();
		System.out.println("SubMenu Inscripciones: ");
		System.out.println("1.Nueva Inscripción");
		System.out.println("2.Listar/ver Inscripciones");
		System.out.println("3.Modificar Inscripción");
		System.out.println("4.Eliminar Inscripción");
		System.out.println("5.Crear archivo con registro");
		System.out.println("0.Salir- Ir atrás.");
		System.out.println();
		System.out.println("Ingrese opcion: ");
		int opcion = sc.nextInt();
		switch (opcion) {
		case 1:

			nuevaInscripcion(sc, connection);
			break;

		case 2:
			listarInscripcion(connection);
			break;
		case 3:
			modificarInscripcion(sc, connection);
			break;
		case 4:
			deleteInscripcion(sc, connection);
			break; // necesito armar los metodos de cada uno ... dentro de cada método hacer
		// las opciones de baja y modificacion

		}
	}

	private static void nuevaInscripcion(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Nueva Inscripción:");
		System.out.println();
		System.out.println("Ingrese id del alumno/estudiante: ");
		int idEstudiante = sc.nextInt();
		Alumno estudiante = AlumnoDAO.finById(idEstudiante, connection);
		if (estudiante == null) {
			System.err.println("Registro inexistente");

		} else {
			System.out.println(estudiante);
			System.out.println("Ingrese id del curso al que se quiere inscribir ->");
			int idCurso = sc.nextInt();
			Curso curso = CursoDAO.findById(idCurso, connection);
			if (curso == null) {
				System.err.println("Registro inexistente");

			} else {
				System.out.println(curso);
				System.out.println();
				System.out.println("Desea realizar esta inscripción?: Y/N");
				String option = sc.next();
				if (option.toUpperCase().equals("Y")) {
					String estado = "activo";
					Inscripcion inscripcion = new Inscripcion(estudiante, curso, estado);
					int insertado = InscripcionDAO.insert(inscripcion, connection);
					if (insertado == 1) {
						System.out.println("Registro creado exitosamente");
					} else {
						System.err.println("Existe un error al cargar la inscripción");
					}
				} else if (option.toUpperCase().equals("N")) {
					System.out.println("Registro no creado");
				}
			}
		}
	}

	private static void deleteInscripcion(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Ingrese ID de la inscripcion a eliminar: ");
		int idInscripcion = sc.nextInt();
//agregar verificacion para verificar que exista el curso

		Inscripcion inscripcionDelete = InscripcionDAO.finById(idInscripcion, connection);

		if (inscripcionDelete != null) {
			int modificadas = InscripcionDAO.delete(idInscripcion, connection);
			if (modificadas == 1) {
				System.out.println("Baja realizada");
			} else {
				System.err.println("No existe id, no se realizo la baja");
			}
		} else {
			System.err.println("No se encontro la inscripción buscada");
		}
	}

	private static void modificarInscripcion(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Ingrese ID de la inscripcion a modificar: ");
		int idInscripcion = sc.nextInt();
//agregar verificacion para verificar que exista el curso

		Inscripcion actualInscripcion = InscripcionDAO.finById(idInscripcion, connection);
		if (actualInscripcion == null) {
			System.err.println("Registro inexistente");
		} else {
			System.out.println(actualInscripcion);
		}
	}

	private static void listarInscripcion(Connection connection) throws SQLException {
		System.out.println("LISTA DE ALUMNOS: ");
		List<Inscripcion> listaInscripcion = InscripcionDAO.findAll(connection);
		for (Inscripcion inscripcion : listaInscripcion) {
			System.out.println(inscripcion.getIdInscripcion() + " - " + inscripcion.getIdEstudiante() + " - "
					+ inscripcion.getEstudiante() + inscripcion.getIdCurso() + " - " + inscripcion.getEstado() + " - "
					+ inscripcion.getComisionCurso());
		}
	}

}
