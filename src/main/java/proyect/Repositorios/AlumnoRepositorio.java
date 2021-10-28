package proyect.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import proyect.Entidades.Alumno;

@Repository
public interface AlumnoRepositorio extends JpaRepository<Alumno, String>{
	
	@Query("SELECT a FROM Alumno a WHERE a.nombreCompleto= :nombreCompleto")
	public List<Alumno> existsByName(@Param("nombreCompleto") String nombreCompleto);
	

}
