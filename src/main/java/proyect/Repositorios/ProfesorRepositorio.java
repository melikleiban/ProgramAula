package proyect.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyect.Entidades.Profesor;

@Repository
public interface ProfesorRepositorio extends JpaRepository<Profesor, String>{

	@Query("SELECT a FROM Profesor a WHERE a.nombreCompleto= :nombreCompleto")
	public List<Profesor> existsByName(@Param("nombreCompleto") String nombreCompleto);

	
}
