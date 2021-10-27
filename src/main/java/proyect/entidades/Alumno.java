package proyect.entidades;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Alumno extends Usuario {
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy="uuid2")
	private String id;
	private List<Curso> cursosActivos;
	private List<Curso> cursosFinalizados;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Curso> getCursosActivos() {
		return cursosActivos;
	}
	public void setCursosActivos(List<Curso> cursosActivos) {
		this.cursosActivos = cursosActivos;
	}
	public List<Curso> getCursosFinalizados() {
		return cursosFinalizados;
	}
	public void setCursosFinalizados(List<Curso> cursosFinalizados) {
		this.cursosFinalizados = cursosFinalizados;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cursosActivos, cursosFinalizados, id);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(cursosActivos, other.cursosActivos)
				&& Objects.equals(cursosFinalizados, other.cursosFinalizados) && Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Alumno [id=" + id + ", cursosActivos=" + cursosActivos + ", cursosFinalizados=" + cursosFinalizados
				+ "]";
	}
	
	
	
	
	
	

}
