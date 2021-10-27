package proyect.entidades;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Foto {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;	
	private String nombre;
	private String mime;
	@Lob @Basic(fetch = FetchType.LAZY)
	private byte[] contenido;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMime() {
		return mime;
	}
	public void setMime(String mime) {
		this.mime = mime;
	}
	public byte[] getContenido() {
		return contenido;
	}
	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(contenido);
		result = prime * result + Objects.hash(id, mime, nombre);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Foto other = (Foto) obj;
		return Arrays.equals(contenido, other.contenido) && Objects.equals(id, other.id)
				&& Objects.equals(mime, other.mime) && Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "Foto [id=" + id + ", nombre=" + nombre + ", mime=" + mime + ", contenido=" + Arrays.toString(contenido)
				+ "]";
	}
	
	
	

}
