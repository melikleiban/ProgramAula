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
	


}
