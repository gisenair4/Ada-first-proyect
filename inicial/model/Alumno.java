package db.inicial.model;

public class Alumno {

	private int idEstudiante;

	private String nameEstudiante;

	private String lastnameEstudiante;

	public Alumno(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public Alumno(String nombreEstudiante) {
		this.nameEstudiante = nombreEstudiante;
	}

	public Alumno(int idEstudiante, String nombreEstudiante, String lastnameEstudiante) {
		this.nameEstudiante = nombreEstudiante;
		this.lastnameEstudiante = lastnameEstudiante;
		this.idEstudiante = idEstudiante;

	}

	public int getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public String getNombreEstudiante() {
		return nameEstudiante;
	}

	public void setNombreEstudiante(String nombreEstudiante) {
		this.nameEstudiante = nombreEstudiante;
	}

	public String getApellidoEstudiante() {
		return lastnameEstudiante;
	}

	public void setApellidoEstudiante(String apellidoEstudiante) {
		this.lastnameEstudiante = apellidoEstudiante;
	}

	public String toString() {
		return "Alumno: " + nameEstudiante + " " + lastnameEstudiante;
	}

}
