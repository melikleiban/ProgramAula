package proyect.Entidades;

import java.util.List;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode (callSuper = true)
public class Alumno extends Usuario {

	private List<Curso> cursosActivos;
	private List<Curso> cursosFinalizados;
	
}
