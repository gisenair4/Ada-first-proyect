package db.inicial.controlator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import db.inicial.LongitudCadenaException;
import db.inicial.DAO.CursoDAO;
import db.inicial.model.Curso;

public class CursoControl {

	private static final int LONGITUD_MINIMA = 2;

	public static void submenuCursos(Scanner sc, Connection connection) throws SQLException, LongitudCadenaException {
		System.out.println();
		System.out.println("SubMenu Curso: ");
		System.out.println("1. Alta curso");
		System.out.println("2.Listar cursos");
		System.out.println("3.Modificar cursos");
		System.out.println("4.Dar de baja cursos");
		System.out.println("5. Buscar Curso por nombre");

		System.out.println("0.Salir");
		System.out.println();
		System.out.println("Ingrese opcion: ");

		int opcion = sc.nextInt();
		while (opcion != 0) {
			switch (opcion) {
			case 1:

				altaCurso(sc, connection);
				break;

			case 2:
				listaCursos(connection);
				break;
			case 3:
				modificarCursos(sc, connection);
				break;
			case 4:
				bajaCursos(sc, connection);
				break; // necesito armar los metodos de cada uno ... dentro de cada método hacer
			// las opciones de baja y modificacion

			case 5:
				buscarCursosPorNombre(sc, connection);
				break;
			}
			System.out.println();
			System.out.println("SubMenu Curso: ");
			System.out.println("1. Alta curso");
			System.out.println("2.Listar cursos");
			System.out.println("3.Modificar cursos");
			System.out.println("4.Dar de baja cursos");
			System.out.println("5. Buscar Curso por nombre");

			System.out.println("0.Salir");
			System.out.println();
			System.out.println("Ingrese opcion: ");
			opcion = sc.nextInt();
		}

	}

	private static void altaCurso(Scanner sc, Connection connection) throws SQLException {
		// Statement stmt = connection.createStatement();
		System.out.println("Ingrese nombre de curso: ");
		String nombreCurso = sc.next();
		Curso curso = new Curso(nombreCurso);
		CursoDAO.insert(curso, connection);// metodo estatico ya que no quiero atributos
		System.out.println("Curso ingresado con éxito!");
		// stmt.executeUpdate("INSERT INTO CURSO (NAME) VALUES('" + nombreCurso +
		// "')");// tiene un sql como string, es
		// decir lo que quiero que
		// ejecute
		// primero hacemos un insert(podemos hacer con update tambien delete o
		// modificar)

	}

	private static void listaCursos(Connection connection) throws SQLException {
		System.out.println("LISTA DE CURSO: ");
		java.util.List<Curso> listaCursos = CursoDAO.findAll(connection);
		for (Curso curso : listaCursos) {
			System.out.println(curso.getIdCurso() + " - " + curso.getName());
		}
	}

	private static void modificarCursos(Scanner sc, Connection connection)
			throws SQLException, LongitudCadenaException {
		// genero la conexión

		System.out.println("Ingrese ID del curso a modificar: ");
		int idCurso = sc.nextInt();
//agregar verificacion para verificar que exista el curso

		Curso actualCurso = CursoDAO.findById(idCurso, connection);
		if (actualCurso == null) {
			System.err.println("Registro inexistente");// verificacion
		} else {
			System.out.println(actualCurso);
			System.out.println();
			System.out.println("Desea modificar o editar este curso?: y/n ");
			String option = sc.next();
			if (option.toUpperCase().equals("Y")) {
				System.out.println();
				System.out.println("Ingrese nuevo nombre de curso: ");

				String nuevoNombre = sc.next();

				if (esValidoLongitud(nuevoNombre, LONGITUD_MINIMA)) {
					Curso curso = new Curso(idCurso, nuevoNombre);
					int updated = CursoDAO.update(curso, connection);// conecto idviejo con el nuevoNombreCurso...

					if (updated == 1) {
						System.out.println("Modificacion realizada ");
					} else {
						System.out.println("Error en la modificacion de curso: actualizados = " + updated);
					}
				} else if (esValidoLongitud(nuevoNombre, LONGITUD_MINIMA) == false) {

					// while (esValidoLongitud(nuevoNombre, LONGITUD_MINIMA) == false) {
					System.out.println("Tu nombre es incorrecto");
					// nuevoNombre = sc.next();

					// }
				}

			}
		}
	}

	private static boolean esValidoLongitud(String nuevoNombre, int longirtudMinima) {

		boolean esValido = nuevoNombre.length() > LONGITUD_MINIMA;

		return esValido;

	}

	private static void buscarCursosPorNombre(Scanner sc, Connection connection) throws SQLException {

		System.out.println("Buscar curso por nombre");
		System.out.println();
		System.out.println("Ingrese nombre del curso: ");
		String nombre = sc.next();
		List<Curso> listaCursos = CursoDAO.findByName(nombre, connection);
		System.out.println();
		listaCursos.forEach(curso -> System.out.println(curso.getIdCurso() + " - " + curso.getName()));
	}

	private static void bajaCursos(Scanner sc, Connection connection) throws SQLException {

		System.out.println("Ingrese ID del curso a darse de baja: ");
		int idCurso = sc.nextInt();
//agregar verificacion para verificar que exista el curso

		Curso cursoDelete = CursoDAO.findById(idCurso, connection);

		if (cursoDelete != null) {
			int modificadas = CursoDAO.delete(idCurso, connection);
			if (modificadas == 1) {
				System.out.println("Baja realizada");
			} else {
				System.err.println("No existe id, no se realizo la baja");
			}
		} else {
			System.err.println("No se encontro el curso");
		}
	}
}
