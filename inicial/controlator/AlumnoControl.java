package db.inicial.controlator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//import db.inicial.sc;
import db.inicial.DAO.AlumnoDAO;
import db.inicial.model.Alumno;

public class AlumnoControl {
	private static final int LONGIRTUD_MINIMA = 2;

	public static void submenuEstudiante(Scanner sc, Connection connection) throws SQLException {

		System.out.println();
		System.out.println("SubMenu Estudiantes: ");
		System.out.println("1. Alta estudiantes");
		System.out.println("2.Listar Estudiantes");
		System.out.println("3.Modificar Estudiantes");
		System.out.println("4.Dar de baja Estudiante");
		System.out.println("5. Buscar Estudiante por nombre");
		System.out.println("0.Salir");
		System.out.println();
		System.out.println("Ingrese opcion: ");

		int opcion = sc.nextInt();
		switch (opcion) {
		case 1:

			altaEstudiante(sc, connection);
			break;

		case 2:
			listaEstudiante(connection);
			break;
		case 3:
			modificarEstudiante(sc, connection);
			break;
		case 4:
			deleteEstudiante(sc, connection);
			break; // necesito armar los metodos de cada uno ... dentro de cada método hacer
		// las opciones de baja y modificacion

		case 5:
			buscarEstudiantePorNombre(sc, connection);
			break;
		}

	}

	private static void buscarEstudiantePorNombre(Scanner sc, Connection connection) throws SQLException {

		System.out.println("Buscar Alumno/Estudinte por nombre");
		System.out.println();
		System.out.println("Ingrese nombre del Alumno/Estudiante: ");
		String nombre = sc.next();
		List<Alumno> listaEstudiante = AlumnoDAO.findByName(nombre, connection);
		System.out.println();
		listaEstudiante
				.forEach(alumno -> System.out.println(alumno.getIdEstudiante() + " - " + alumno.getNombreEstudiante()));

	}

	private static void findBylastName(Scanner sc, Connection connection) throws SQLException {

		System.out.println("Buscar Alumno/Estudinte por Apellido");
		System.out.println();
		System.out.println("Ingrese Apellido del Alumno/Estudiante: ");
		String lastname = sc.next();
		List<Alumno> listaEstudiante = AlumnoDAO.findBylastName(lastname, connection);
		System.out.println();
		listaEstudiante
				.forEach(alumno -> System.out.println(alumno.getIdEstudiante() + " - " + alumno.getNombreEstudiante()));
	}

	private static void deleteEstudiante(Scanner sc, Connection connection) throws SQLException {

		System.out.println("Ingrese ID del Alumno/Estudiante a darse de baja: ");
		int idEstudiante = sc.nextInt();
//agregar verificacion para verificar que exista el curso

		Alumno estudianteDelete = AlumnoDAO.finById(idEstudiante, connection);

		if (estudianteDelete != null) {
			int modificadas = AlumnoDAO.delete(idEstudiante, connection);
			if (modificadas == 1) {
				System.out.println("Baja realizada");
			} else {
				System.err.println("No existe id, no se realizo la baja");
			}
		} else {
			System.err.println("No se encontro el curso");
		}
	}

	private static void modificarEstudiante(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Ingrese ID del alumno a modificar: ");
		int idEstudiante = sc.nextInt();
//agregar verificacion para verificar que exista el curso

		Alumno actualEstudiante = AlumnoDAO.finById(idEstudiante, connection);
		if (actualEstudiante == null) {
			System.err.println("Registro inexistente");
		} else {
			System.out.println(actualEstudiante);
			System.out.println();
			System.out.println("Desea modificar o editar este curso?: y/n ");
			String option = sc.next();
			if (option.toUpperCase().equalsIgnoreCase(" y ")) {
				System.out.println();
				System.out.println("Ingrese nuevo nombre del alumno: ");

				String nuevoNombre = sc.next();

				Alumno alumno = new Alumno(nuevoNombre);
				alumno.setIdEstudiante(idEstudiante);
				int updated = AlumnoDAO.update(alumno, connection);

				while (nuevoNombre.length() < LONGIRTUD_MINIMA) { // validar el nuevo nombre -> cantidad minima de
																	// caracteres

					validarLongitud(nuevoNombre, LONGIRTUD_MINIMA);
				}
				if (updated == 1) {
					System.out.println("Modificacion realizada ");
				} else {
					System.out.println("Error en la modificacion de curso: actualizados = " + updated);
				}

			}
		}
	}

	private static void validarLongitud(String nuevoNombre, int longirtudMinima) {
		Scanner sc = new Scanner(System.in);
		String nuevoNombre1 = "";
		while (nuevoNombre.length() < LONGIRTUD_MINIMA) {

		}

	}

	private static void altaEstudiante(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Sistema de alta nuevo estudiante/alumno:");
		System.out.println();
		System.out.println("Ingrese nombre del estudiante: Nombre ");
		String nombreEstudiante = sc.next();
		System.out.println("Ingrese Apellido del alumno: ");
		String lastnameEstudiante = sc.next();
		Alumno estudiante1 = new Alumno(nombreEstudiante);
		AlumnoDAO.insert(estudiante1, connection);

		Alumno estudiante2 = new Alumno(lastnameEstudiante);

		AlumnoDAO.insert(estudiante2, connection);
	}

	private static void listaEstudiante(Connection connection) throws SQLException {
		System.out.println("LISTA DE ALUMNOS: ");
		List<Alumno> listaEstudiantes = AlumnoDAO.findAll(connection);
		for (Alumno estudiante : listaEstudiantes) {
			System.out.println(estudiante.getIdEstudiante() + " - " + estudiante.getNombreEstudiante() + " - "
					+ estudiante.getApellidoEstudiante());
		}
	}

}
