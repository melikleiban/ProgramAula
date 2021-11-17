package proyect.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

//import org.springframework.web.multipart.MultipartFile;
import proyect.ErrorServicio.ErrorServicio;
import proyect.Servicios.UsuarioServicio;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@RequestMapping("/usuario")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	
	@GetMapping("/perfilalumno")
	public String perfilAlumno() {
		
		return "perfilAlumno.html";
	}
	
	@GetMapping("/perfilalumno/editar")
	public String perfilAlumnoEditar() {
		
		return "perfilAlumnoEditar.html";
	}
	
	@PostMapping("/perfilalumno/editar")
	public String perfilAlumnoEditar(ModelMap modelo,
			String nombreUsuario, 
			String nombreCompleto,
			String email,
			String telefono,	
			String localidad,
			String contrasenia,
			String descripcion,
			MultipartFile archivo)
					throws ErrorServicio {
		
		try {
			
			usuarioServicio.modificar(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia, descripcion, archivo);
		
			
		} catch (ErrorServicio ex) {
			modelo.put("error", ex.getMessage());
			
		}
		
		return "perfilAlumno.html";
		
	}

	@GetMapping("/perfildocente")
	public String perfilProfesor() {
	
		return "perfilDocente.html";
	}
	
	@PostMapping("/perfilprofesor/editar")
	public String perfilProfesorEditar(
				ModelMap modelo,
				String nombreUsuario, 
				String nombreCompleto,
				String email,
				String telefono,	
				String localidad,
				String contrasenia,
				String descripcion,
				MultipartFile archivo)
						throws ErrorServicio {
			try {
				
				usuarioServicio.modificar(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia, descripcion, archivo);
				
			} catch (ErrorServicio ex) {
				modelo.put("error", ex.getMessage());
			}
	
		return "perfilDocente.html";
	}

//	
//	EN EL METODO DE USUARIOSERVICIO LOADUSERBYUSERNAME ESE PERMISO. VIDEO 7MINUTO 2.18

}
