package proyect.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyect.Entidades.Foto;

@Repository
public interface FotoRepositorio extends JpaRepository<Foto, String> {
	
	
}
