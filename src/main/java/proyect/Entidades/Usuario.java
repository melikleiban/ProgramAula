package proyect.Entidades;



import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import proyect.Enums.Rol;

@Entity
@Data
public class Usuario {

	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy="uuid2")
	private String id;


	@Column(nullable=false,unique=true)
	private String nombreUsuario;

	private String nombreCompleto;
	private String email;
	private String telefono;
	private String localidad;
	//private String descripcion;
	private Boolean altaBaja;
	private Boolean login;

	@OneToOne
	private Foto foto;

	@Enumerated(EnumType.STRING)
	private Rol rol;

	@Column(nullable=false)
	private String contrasenia;

	@OneToMany
	private List<Curso> listaCursos;

	

	
	
	
}
