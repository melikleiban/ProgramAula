package proyect.Servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import proyect.Entidades.Alumno;
import proyect.Entidades.Profesor;

import proyect.ErrorServicio.ErrorServicio;
import proyect.Repositorios.AlumnoRepositorio;
import proyect.Repositorios.ProfesorRepositorio;

@Service
public class UsuarioServicio {
	@Autowired
	ProfesorRepositorio profesorRepositorio;
	@Autowired
	AlumnoRepositorio alumnoRepositorio;
	
	@Transactional
	public void registro(String nombreUsuario, 
			String nombreCompleto,
			String email,
			String telefono,
			String localidad,
			String contrasenia, 
			Boolean EsProfesor) throws ErrorServicio{
		validar(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia);
		if(EsProfesor==true) {
			Profesor profesor = new Profesor();
			profesor.setNombreUsuario(nombreUsuario);
			profesor.setNombreCompleto(nombreCompleto);
			profesor.setEmail(email);
			profesor.setTelefono(telefono);
			profesor.setLocalidad(localidad);
			profesor.setContraseña(contrasenia);
			try {
				profesorRepositorio.save(profesor);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		
		} else {
			Alumno alumno = new Alumno();
			alumno.setNombreUsuario(nombreUsuario);
			alumno.setNombreCompleto(nombreCompleto);
			alumno.setEmail(email);
			alumno.setTelefono(telefono);
			alumno.setLocalidad(localidad);
			alumno.setContraseña(contrasenia);
			try {
				alumnoRepositorio.save(alumno);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}
	
	
	public void validar(String nombreUsuario, 
			String nombreCompleto,
			String email,
			String telefono,
			String localidad,
			String contrasenia)throws ErrorServicio{
		
		if(nombreUsuario==null || nombreUsuario.isEmpty()) {
			throw new ErrorServicio ("El nombre de usuario no puede ser nulo/vacío");
		}
		if(nombreCompleto==null || nombreCompleto.isEmpty() || nombreCompleto.length()< 3) {
			throw new ErrorServicio ("El nombre no pude ser nulo/vacio/o menor a 3 caracteres");
		}
		if (email == null || email.isEmpty() || email.contains("  ")) {
			throw new ErrorServicio("Debe tener un email valido");
		}
		if (telefono == null || telefono.isEmpty() || contrasenia.contains("  ")) {
			throw new ErrorServicio("Debe tener un telefono valido");
		}
		if (localidad == null || localidad.isEmpty()) {
			throw new ErrorServicio("Debe tener una localidad valida");
		}
		if (contrasenia == null || contrasenia.isEmpty() || contrasenia.contains("  ") || contrasenia.length() < 8 || contrasenia.length() > 12) {
			throw new ErrorServicio("Debe tener una contraseña valida");
		}
		
	}

}
