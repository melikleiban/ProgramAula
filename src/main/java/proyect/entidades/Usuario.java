package proyect.Entidades;



import javax.persistence.Column;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Usuario {
	
	@NonNull
	@Column(nullable=false,unique=true)
	protected String nombreUsuario;
	
	protected String nombreCompleto;
	protected String email;
	protected String telefono;
	protected String localidad;
	
	@NonNull
	protected String contraseña;

}
