package proyect.entidades;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Curso {
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy="uuid2")
	private String idCurso;
	private String titulo;
	private Boolean altaBaja;
	private Double precioPorHora;
	private String nivelDificultad;
	private String descripcion;
	private List<Alumno> alumnos;
	
	@ManyToOne
	private String idProfesor;

	public String getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Boolean getAltaBaja() {
		return altaBaja;
	}

	public void setAltaBaja(Boolean altaBaja) {
		this.altaBaja = altaBaja;
	}

	public Double getPrecioPorHora() {
		return precioPorHora;
	}

	public void setPrecioPorHora(Double precioPorHora) {
		this.precioPorHora = precioPorHora;
	}

	public String getNivelDificultad() {
		return nivelDificultad;
	}

	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public String getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(String idProfesor) {
		this.idProfesor = idProfesor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(altaBaja, alumnos, descripcion, idCurso, idProfesor, nivelDificultad, precioPorHora,
				titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(altaBaja, other.altaBaja) && Objects.equals(alumnos, other.alumnos)
				&& Objects.equals(descripcion, other.descripcion) && Objects.equals(idCurso, other.idCurso)
				&& Objects.equals(idProfesor, other.idProfesor)
				&& Objects.equals(nivelDificultad, other.nivelDificultad)
				&& Objects.equals(precioPorHora, other.precioPorHora) && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Curso [idCurso=" + idCurso + ", titulo=" + titulo + ", altaBaja=" + altaBaja + ", precioPorHora="
				+ precioPorHora + ", nivelDificultad=" + nivelDificultad + ", descripcion=" + descripcion + ", alumnos="
				+ alumnos + ", idProfesor=" + idProfesor + "]";
	}
	
	
	
	
	
	

}
