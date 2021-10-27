package proyect.entidades;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


import lombok.NonNull;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy="uuid2")
	private String id;
	@NonNull
	@Column(nullable=false,unique=true)
	private String nombreUsuario;
	private String nombreCompleto;
	private String email;
	private String telefono;
	private String localidad;
	@NonNull
	private String contraseña;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	@Override
	public int hashCode() {
		return Objects.hash(contraseña, email, id, localidad, nombreCompleto, nombreUsuario, telefono);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(contraseña, other.contraseña) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(localidad, other.localidad)
				&& Objects.equals(nombreCompleto, other.nombreCompleto)
				&& Objects.equals(nombreUsuario, other.nombreUsuario) && Objects.equals(telefono, other.telefono);
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", nombreCompleto=" + nombreCompleto
				+ ", email=" + email + ", telefono=" + telefono + ", localidad=" + localidad + ", contraseña="
				+ contraseña + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
