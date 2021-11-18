package proyect.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyect.Entidades.Curso;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, String>{
	
	@Query("SELECT a FROM Curso a WHERE a.titulo= :titulo")
	public List<Curso> existsByName(@Param("titulo") String titulo);
	
	@Query("SELECT a FROM Curso a WHERE a.nivelDificultad= :nivelDificultad")
	public List<Curso> existByDificultad(@Param("nivelDificultad") String nivelDificultad);
	
	@Query("SELECT a FROM Curso a WHERE a.descripcion LIKE %:clave%")
	public List<Curso> existsByClave (@Param("clave")String clave);
	
	@Query("SELECT a FROM Curso a WHERE a.lenguajes= :b ")
	public List<Curso> existsByLenguajes (@Param("b")String lenguajes);

	@Query("SELECT a FROM Curso a WHERE a.lenguajes= :b AND a.descripcion LIKE %:clave%")
	public List<Curso> existsByAmbas (@Param("b")String lenguajes, @Param("clave")String clave);
	

//	@Query("SELECT c FROM Curso c INNER JOIN Usuario u ON c.profesor_id = u.id WHERE c.profesor_id = :p")
//	public List<Curso> existsByProfesor (@Param("p")String profesorId);

	@Query("SELECT profesor_id= :p FROM Curso")
	public List<Curso> existsByProfesor (@Param("p")String profesorId);
	
	
//	SELECT Nombre, Nombre_curso FROM Profesores INNER JOIN Cursos ON 
//	Profesores.Id = Cursos.Id_profesor;

}