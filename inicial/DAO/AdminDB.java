package db.inicial.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminDB {
//esta clase solo obtiene la conexion con mysql y nada mas...
	public static Connection obtenerConexion() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");// busca una clase en memoria
		// com\mysql\cj\jdbc

		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cursos?serverTimezone=UTC", "root", "");
//devuelve la clase y la conexion y para eso le pasamo 3 parametros: la url, usuario y clave
	}

}
