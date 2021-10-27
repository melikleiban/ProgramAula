package proyect.entidades;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class Profesor extends Usuario {

	
	private String descripcion;
	private List<Curso> cursosCargados;
	
	private Foto foto;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Curso> getCursosCargados() {
		return cursosCargados;
	}
	public void setCursosCargados(List<Curso> cursosCargados) {
		this.cursosCargados = cursosCargados;
	}
	
	public Foto getFoto() {
		return foto;
	}
	public void setFoto(Foto foto) {
		this.foto = foto;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cursosCargados, descripcion, foto);
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
		Profesor other = (Profesor) obj;
		return Objects.equals(cursosCargados, other.cursosCargados) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(foto, other.foto);
	}
	@Override
	public String toString() {
		return "Profesor [descripcion=" + descripcion + ", cursosCargados=" + cursosCargados + ", foto=" + foto + "]";
	}
	
	

	
	
	
	
	
	
	
	
}
