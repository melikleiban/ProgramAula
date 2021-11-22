package proyect.Servicios;


import java.util.List;
//import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyect.Configuraciones.ConfiguracionEmail;
import proyect.Entidades.Curso;
import proyect.Entidades.Usuario;
import proyect.Enums.Lenguajes;
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

	@Autowired
	private ConfiguracionEmail configuracionEmail;
	
	@Transactional
	public void crearCurso(String nombreUsuario, String titulo, Boolean altaBaja, Double precioPorHora,
			String nivelDificultad, String descripcion, Lenguajes lenguajes) throws ErrorServicio {

		try {
			Optional<Usuario> usuario = Optional.ofNullable(usuarioRepositorio.buscarPorNombreUsuario(nombreUsuario));
			if (usuario.isPresent() && usuario.get().getRol() == Rol.PROFESOR) {

				validar(titulo, precioPorHora, nivelDificultad, descripcion);

				System.out.println(nombreUsuario+titulo+altaBaja+precioPorHora+nivelDificultad+descripcion+lenguajes);
				Curso curso = new Curso();
				curso.setTitulo(titulo);
				curso.setAltaBaja(true);
				curso.setPrecioPorHora(precioPorHora);
				curso.setNivelDificultad(nivelDificultad);
				curso.setDescripcion(descripcion);
				curso.setLenguajes(lenguajes);
				curso.setProfesor(usuario.get());
				curso.setId_profesor(usuario.get().getId());
				cursoRepositorio.save(curso);
								
				usuario.get().getListaCursos().add(curso);
				
				usuarioRepositorio.save(usuario.get());
				
				

			}
		} catch (Exception e) {
			System.err.print(e.getMessage());
		}
	}

	public List<Curso> cursosProfesor(String profesor_id) {
	
		//Optional<Usuario> respuesta = usuarioRepositorio.findById(idProfesor);
		
		List <Curso> listaCursos = cursoRepositorio.findByProfesor(profesor_id);
		
		return listaCursos;
		
		
//		
//		if(respuesta.isPresent()) {
//			Usuario usuario = respuesta.get();
//			List<Curso> listaCursos = usuario.getListaCursos();
//			System.out.println("Entra al if");
//			return listaCursos;
//		}
//		return null;
	}
		
	
	@Transactional
	public void bajaCurso(String idCurso) throws ErrorServicio {
		Optional<Curso> respuesta = cursoRepositorio.findById(idCurso);
		if (respuesta.isPresent()) {
			Curso curso = respuesta.get();
			curso.setAltaBaja(false);
			
			cursoRepositorio.save(curso);
		} else {
			throw new ErrorServicio("No existe el curso buscado.");
		}
	}

	

	public void alertaProfesor(String idAlumno, String idCurso, String mensaje) {
		Optional<Usuario> result = usuarioRepositorio.findById(idAlumno);
	
		System.out.println("entra al servicio");
		if (result.isPresent() && result.get().getRol() == Rol.ALUMNO) {
		
			Optional<Curso> resultCurso = cursoRepositorio.findById(idCurso);
			
			configuracionEmail.emailSender("El usuario " + result.get().getNombreUsuario() 
					+ " de nombre " + result.get().getNombreCompleto()
					+ " solicita acceso a su curso titulado: " + resultCurso.get().getTitulo()
					+ "\n"
					+ "Email de contacto: " + result.get().getEmail()
					+ "\n"
					+ "Teléfono de contacto: " + result.get().getTelefono()+ "." 
					+ "\n" 
					+ "Mensaje: " +mensaje,
					"Alerta de inscripición", resultCurso.get().getProfesor().getEmail());
			

		}
	}

	
	
	@Transactional
	public void cargarAlumno(String idCurso, String nombreUsuario) throws ErrorServicio {
		Optional<Curso> resultCurso = cursoRepositorio.findById(idCurso);
		Optional<Usuario> result = Optional.ofNullable(usuarioRepositorio.buscarPorNombreUsuario(nombreUsuario));
		if (resultCurso.isPresent() && result.get().getRol() == Rol.ALUMNO) {
			resultCurso.get().getAlumnosInscriptos().add(result.get());
			result.get().getListaCursos().add(resultCurso.get());
			usuarioRepositorio.save(result.get());
			cursoRepositorio.save(resultCurso.get());
		} else {
			throw new ErrorServicio("Se ha producido un error en la solicitud.");
		}

	}

	public void calificarCurso(String nombreUsuario, String idCurso, Integer valoracion) throws ErrorServicio {
		Optional<Usuario> result = Optional.ofNullable(usuarioRepositorio.buscarPorNombreUsuario(nombreUsuario));
		Optional<Curso> resultCurso = cursoRepositorio.findById(idCurso);

		if (result.isPresent() && result.get().getRol() == Rol.ALUMNO) {

			Curso curso = resultCurso.get();

			curso.setCantidadValoracion(curso.getCantidadValoracion() + 1);
			curso.setTotalValoracion(curso.getTotalValoracion() + valoracion);

			curso.setPromedioValoracion(
					(Integer) Math.round(curso.getTotalValoracion() / curso.getCantidadValoracion()));

			cursoRepositorio.save(resultCurso.get());

		} else {
			throw new ErrorServicio("Se ha producido un error en la solicitud.");
		}
	}
	
	public List<Curso> listarCurso(){
		List<Curso> cursos=cursoRepositorio.findAll();
		return cursos;
	}
	
	public List<Curso> listarCursoPorPalabraClave(String palabraClave){
		List<Curso> cursos=cursoRepositorio.existsByClave(palabraClave);
		return cursos;
	}
	
	
	

	public void validar(String titulo, Double precioPorHora, String nivelDificultad, String descripcion)
			throws ErrorServicio {

		if (titulo == null || titulo.isEmpty()) {
			throw new ErrorServicio("Debe ingresar un tÃ­tulo.");
		}
		if (precioPorHora == null) {
			throw new ErrorServicio("Debe ingresar un precio por hora.");
		}

		if (nivelDificultad == null || nivelDificultad.isEmpty()) {
			throw new ErrorServicio("Debe ingresar un nivel de dificultad.");
		}

		if (descripcion == null || descripcion.isEmpty()) {
			throw new ErrorServicio("Debe ingresar una descripciÃ³n.");
		}

	}

	
//	public List<Curso> FiltrarCursos(String PalabraClave, String lenguajes) {
//		List<Curso> listaCurso;
//		if (PalabraClave.isEmpty() || PalabraClave == null && lenguajes == null) {
//			listaCurso = cursoRepositorio.findAll();
//
//		} else if (lenguajes == null) {
//
//			listaCurso = cursoRepositorio.existsByClave(PalabraClave);
//
//		} else if (PalabraClave == null || PalabraClave.isEmpty()) {
//			listaCurso = cursoRepositorio.existsByLenguajes(lenguajes);
//		} else {
//			listaCurso = cursoRepositorio.existsByAmbas(lenguajes, PalabraClave);			
//		}
//		return listaCurso;
//	}


}
