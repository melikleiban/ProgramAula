package proyect.Entidades;


import java.util.List;



import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import lombok.Data;
import proyect.Enums.Lenguajes;

@Entity
@Data
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
	private Integer promedioValoracion;
	private Integer cantidadValoracion;
	private Integer totalValoracion;
	@Enumerated(EnumType.STRING)
	private Lenguajes lenguajes;
	
		
	@OneToMany
	private List<Usuario> alumnosInscriptos;

	@ManyToOne
	private Usuario profesor;
}
