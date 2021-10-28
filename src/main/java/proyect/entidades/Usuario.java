package proyect.Entidades;



import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Usuario {
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy="uuid2")
	protected String id;
	
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
