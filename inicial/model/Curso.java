package db.inicial.model;

public class Curso {

	private int idCurso;

	private String name;

	public Curso(String nombreCurso) {
		this.name = nombreCurso;
	}

	public Curso(int idCurso, String name) {
		this.name = name;
		this.idCurso = idCurso;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Curso: " + this.name;
	}
}
