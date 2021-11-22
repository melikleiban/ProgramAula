package proyect.Repositorios;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import proyect.Entidades.Curso;
import proyect.Entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{

	@Query("SELECT u FROM Usuario u WHERE u.nombreCompleto= :nombreCompleto")
	public List<Usuario> existsByName(@Param("nombreCompleto") String nombreCompleto);

	@Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
	public Usuario buscarPorNombreUsuario(@Param("nombreUsuario") String nombreUsuario);

	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	public Usuario buscarPorEmail(@Param("email") String email);
	
	@Query("SELECT profesor_id= :p FROM Curso")
	public List<Curso> existsByProfesor (@Param("p")String profesorId);
	
	
}
