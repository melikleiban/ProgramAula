package proyect.Entidades;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

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
	
	@ManyToOne
	private Profesor profesor;
	
	@ManyToMany
	private List<Alumno> alumnosInscriptos;

}
