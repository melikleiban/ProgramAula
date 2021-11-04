package proyect.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
import proyect.ErrorServicio.ErrorServicio;
import proyect.Servicios.UsuarioServicio;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	
	@GetMapping("/registro")
	public String registro() {
		return "registro-alumno-profesor.html";
	}	
	
	
	@GetMapping("/registro/profesor")
	public String registroProfesor() {
		return "registro-profesor.html";
	}
	
	
	@PostMapping("/registro/profesor")
	public String registroProfesor(ModelMap modelo,
			@RequestParam(required = false) String nombreUsuario,
			@RequestParam(required = false) String nombreCompleto,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String telefono,
			@RequestParam(required = false) String localidad,
			@RequestParam(required = false) String descripcion,
			@RequestParam(required = false) String contrasenia) throws ErrorServicio {
		
		try {
			
			usuarioServicio.registro(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia,descripcion, true);
			modelo.put("exito", "registro exitoso");
			return "redirect:/";
			
		}catch(Exception e){
			modelo.put("error", e.getMessage());
				return "registro-alumno-profesor.html";		
		}

	}
	 
	@GetMapping("/registro/alumno")
	public String registroAlumno() {
		return "registro-alumno.html";
	}
	
	
	@PostMapping("/registro/alumno")
	public String registro(ModelMap modelo,
			@RequestParam(required = false) String nombreUsuario,
			@RequestParam(required = false) String nombreCompleto,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String telefono,
			@RequestParam(required = false) String localidad,
			@RequestParam(required = false) String descripcion,
			@RequestParam(required = false) String contrasenia) throws ErrorServicio {
		try {
			
			
			usuarioServicio.registro(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia,descripcion, false);
			modelo.put("exito", "registro exitoso");
			return "redirect:/";
			
		}catch(Exception e){
			modelo.put("error", e.getMessage());
				return "registro-alumno-profesor.html";		
		}

	}
	
	
	
	
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "logout.html";
	}
	
	

	
	
	
	
	

}
