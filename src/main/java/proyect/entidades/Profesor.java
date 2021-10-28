package proyect.Entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode (callSuper = true)
public class Profesor extends Usuario {


	private String descripcion;
	
	private List<Curso> cursosCargados;
	
	@OneToOne
	private Foto foto;

	
}
