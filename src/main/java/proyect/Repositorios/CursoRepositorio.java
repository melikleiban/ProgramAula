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
	

	@Query("SELECT p FROM Curso p WHERE p.profesor_id LIKE :d")
	public List<Curso> findByProfesor(@Param("d")String profesor_id);
	
	


}