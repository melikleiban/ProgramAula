package proyect.Servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyect.Entidades.Curso;
import proyect.Entidades.Usuario;
import proyect.Enums.Rol;
import proyect.ErrorServicio.ErrorServicio;
import proyect.Repositorios.CursoRepositorio;
import proyect.Repositorios.UsuarioRepositorio;

@Service
public class CursoServicio {
	
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	public void crearCurso(String nombreUsuario, 
			String titulo, 
			Boolean altaBaja, 
			Double precioPorHora, 
			String nivelDificultad, 
			String descripcion) throws ErrorServicio {
		
		try {
			Optional<Usuario> usuario = Optional.ofNullable(usuarioRepositorio.buscarPorNombreUsuario(nombreUsuario));
			if(usuario.isPresent() && usuario.get().getRol()== Rol.PROFESOR) {
				
				validar(titulo,precioPorHora,nivelDificultad,descripcion);
				
				Curso curso = new Curso();
				curso.setTitulo(titulo);
				curso.setAltaBaja(true);
				curso.setPrecioPorHora(precioPorHora);
				curso.setNivelDificultad(nivelDificultad);
				curso.setDescripcion(descripcion);
					
				cursoRepositorio.save(curso);
				
			}
		}catch(Exception e){
			System.err.print(e.getMessage());
			
		}
		
		
		
	}

	
	public void bajaCurso(String idCurso) throws ErrorServicio {
		Optional <Curso> respuesta = cursoRepositorio.findById(idCurso);
		if (respuesta.isPresent()) {
			Curso curso = respuesta.get();
			curso.setAltaBaja(false);
			
			cursoRepositorio.save(curso);
		}else {
			throw new ErrorServicio("No existe el curso buscado.");
		}
		
		
		
		
	}
	
	public void validar(String titulo, 
			Double precioPorHora, 
			String nivelDificultad, 
			String descripcion)throws ErrorServicio{

		if(titulo==null || titulo.isEmpty()) {
			throw new ErrorServicio ("Debe ingresar un título.");
		}
		if(precioPorHora==null) {
			throw new ErrorServicio ("Debe ingresar un precio por hora.");
		}
		
		if(nivelDificultad==null || nivelDificultad.isEmpty()) {
			throw new ErrorServicio ("Debe ingresar un nivel de dificultad.");
		}
		
		if(descripcion==null || descripcion.isEmpty()) {
			throw new ErrorServicio ("Debe ingresar una descripción.");
		}
		
	}
	
	
	
	

}
