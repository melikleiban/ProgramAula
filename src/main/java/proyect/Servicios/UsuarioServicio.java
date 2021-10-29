package proyect.Servicios;

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
	AlumnoRepositorio alumnoRepositorio;
	
	@Autowired
	ProfesorRepositorio profesorRepositorio;
	
	public void registro(String nombreUsuario, 
			String nombreCompleto,
			String email,
			String telefono,
			String localidad,
			String contrasenia,
			Boolean esProfesor) throws ErrorServicio{
		validar(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia);
		if (esProfesor == true) {
			Profesor profesor = new Profesor();
			profesor.setNombreUsuario(nombreUsuario);
			profesor.setNombreCompleto(nombreCompleto);
			profesor.setEmail(email);
			profesor.setTelefono(telefono);
			profesor.setLocalidad(localidad);
			//profesor.setContrasenia(contrasenia) -> Spring Security
			
			try {
				profesorRepositorio.save(profesor);
			} catch( Exception e ) {
				e.printStackTrace();;
			}
			
		} else {
			Alumno alumno = new Alumno();
			alumno.setNombreUsuario(nombreUsuario);
			alumno.setNombreCompleto(nombreCompleto);
			alumno.setEmail(email);
			alumno.setTelefono(telefono);
			alumno.setLocalidad(localidad);
			//alumno.setContrasenia(contrasenia) -> Spring Security
			
			try {
				alumnoRepositorio.save(alumno);
			} catch( Exception e ) {
				e.printStackTrace();;
			
			
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
			throw new ErrorServicio ("El nombre de usuario no puede ser nulo/vacío.");
		}
		if(nombreCompleto==null || nombreCompleto.isEmpty() || nombreCompleto.length() < 3 ) {
			throw new ErrorServicio ("El nombre de la persona no puede estar vacío o contener menos de 3 letras.");
		}
		if(email==null || email.isEmpty() || email.contains(" ") || !email.contains("@")) {
			throw new ErrorServicio ("El email debe ser válido.");
		}
		if(telefono==null || telefono.isEmpty()) {
			throw new ErrorServicio ("El numero de teléfono debe ser válido.");
		}
		if(localidad==null || localidad.isEmpty()) {
			throw new ErrorServicio ("La localidad no puede estar vacía.");
		}
		if(contrasenia==null || contrasenia.isEmpty() || contrasenia.contains(" ") || contrasenia.length() < 8 || contrasenia.length() > 12) {
			throw new ErrorServicio ("La contraseña debe poseer entre 8 y 12 caracteres válidos.");
		}
		
	}

}
