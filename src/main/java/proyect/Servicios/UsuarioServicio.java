package proyect.Servicios;

import org.springframework.stereotype.Service;

import proyect.Entidades.Usuario;
import proyect.ErrorServicio.ErrorServicio;

@Service
public class UsuarioServicio {
	
	public void registro(String nombreUsuario, 
			String nombreCompleto,
			String email,
			String telefono,
			String localidad,
			String contrasenia) throws ErrorServicio{
		
		
		
		
		
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
		
	}

}
