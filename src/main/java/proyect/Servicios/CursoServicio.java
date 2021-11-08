package proyect.Servicios;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private NotificacionServicio notificacionServicio;

	@Transactional
	public void crearCurso(String nombreUsuario, String titulo, Boolean altaBaja, Double precioPorHora,
			String nivelDificultad, String descripcion, Lenguajes lenguajes) throws ErrorServicio {

		try {
			Optional<Usuario> usuario = Optional.ofNullable(usuarioRepositorio.buscarPorNombreUsuario(nombreUsuario));
			if (usuario.isPresent() && usuario.get().getRol() == Rol.PROFESOR) {

				validar(titulo, precioPorHora, nivelDificultad, descripcion);

				Curso curso = new Curso();
				curso.setTitulo(titulo);
				curso.setAltaBaja(true);
				curso.setPrecioPorHora(precioPorHora);
				curso.setNivelDificultad(nivelDificultad);
				curso.setDescripcion(descripcion);
				curso.setLenguajes(lenguajes);

				cursoRepositorio.save(curso);
			}
		} catch (Exception e) {
			System.err.print(e.getMessage());
		}
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

	public void validar(String titulo, Double precioPorHora, String nivelDificultad, String descripcion)
			throws ErrorServicio {

		if (titulo == null || titulo.isEmpty()) {
			throw new ErrorServicio("Debe ingresar un título.");
		}
		if (precioPorHora == null) {
			throw new ErrorServicio("Debe ingresar un precio por hora.");
		}

		if (nivelDificultad == null || nivelDificultad.isEmpty()) {
			throw new ErrorServicio("Debe ingresar un nivel de dificultad.");
		}

		if (descripcion == null || descripcion.isEmpty()) {
			throw new ErrorServicio("Debe ingresar una descripción.");
		}

	}

	public void alertaProfesor(String nombreUsuario, String idCurso) {
		Optional<Usuario> result = Optional.ofNullable(usuarioRepositorio.buscarPorNombreUsuario(nombreUsuario));
		if (result.isPresent() && result.get().getRol() == Rol.ALUMNO) {
			Optional<Curso> resultCurso = cursoRepositorio.findById(idCurso);
			notificacionServicio.enviar(
					"El usuario " + nombreUsuario + " de nombre " + result.get().getNombreCompleto()
							+ " solicita acceso a su curso de " + resultCurso.get().getTitulo() + ".",
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


	public List<Curso> FiltrarCursos(String PalabraClave, String lenguajes) {
		List<Curso> listaCurso;
		if (PalabraClave.isEmpty() || PalabraClave == null && lenguajes == null) {
			listaCurso = cursoRepositorio.findAll();

		} else if (lenguajes == null) {

			listaCurso = cursoRepositorio.existsByClave(PalabraClave);

		} else if (PalabraClave == null || PalabraClave.isEmpty()) {
			listaCurso = cursoRepositorio.existsByLenguajes(lenguajes);
		} else {
			listaCurso = cursoRepositorio.existsByAmbas(lenguajes, PalabraClave);

		}
		return listaCurso;

	}

	
}
