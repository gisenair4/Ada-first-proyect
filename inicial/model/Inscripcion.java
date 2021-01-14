package db.inicial.model;

public class Inscripcion {

	private int idInscripcion;
	private int idEstudiante;
	private int idProfesor;
	private int idCurso;
	private String comisionCurso;
	private int notaParcial;
	private int notaFinal;
	private Alumno estudiante;
	private Curso curso;
	private String estado;

	public Inscripcion(int idInscripcion, int idEstudiante, int idProfesor, int idCurso, String comisionCurso,
			int notaParcial, int notaFinal, String estado) {
		this.idInscripcion = idInscripcion;
		this.idEstudiante = idEstudiante;
		this.idProfesor = idProfesor;
		this.idCurso = idCurso;
		this.comisionCurso = comisionCurso;
		this.notaParcial = notaParcial;
		this.notaFinal = notaFinal;
		this.estado = estado;

	}

	public Inscripcion(Alumno estudiante, Curso curso, String estado) {

		this.estudiante = estudiante;
		this.curso = curso;
		this.estado = estado;
	}

	public String getComisionCurso() {
		return comisionCurso;
	}

	public void setComisionCurso(String comisionCurso) {
		this.comisionCurso = comisionCurso;
	}

	public Alumno getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Alumno estudiante) {
		this.estudiante = estudiante;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIdInscripcion() {
		return idInscripcion;
	}

	public void setIdInscripcion(int idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

	public int getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public int getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public int getNotaParcial() {
		return notaParcial;
	}

	public void setNotaParcial(int notaParcial) {
		this.notaParcial = notaParcial;
	}

	public int getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(int notaFinal) {
		this.notaFinal = notaFinal;
	}

}
