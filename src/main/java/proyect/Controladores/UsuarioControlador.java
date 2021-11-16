package proyect.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
			//@RequestParam(required = false) String descripcion,
			@RequestParam(required = false) String contrasenia
			//@RequestParam(required = false) String contrasenia2
			) throws ErrorServicio {
		
		try {
			
			usuarioServicio.registro(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia, true);
			modelo.put("exito", "registro exitoso");
			return "inicio.html";
			
		}catch(Exception e){
			modelo.put("error", e.getMessage());			
				return "registro-profesor.html";		
		}

	}
	 
	@GetMapping("/registro/alumno")
	public String registroAlumno() {
		return "registro-alumno.html";
	}
		
	@PostMapping("/registro/alumno")
	public String registroAlumno(ModelMap modelo,
			@RequestParam(required = false) String nombreUsuario,
			@RequestParam(required = false) String nombreCompleto,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String telefono,
			@RequestParam(required = false) String localidad,
			//@RequestParam(required = false) String descripcion,
			@RequestParam(required = false) String contrasenia,
			@RequestParam(required = false) String contrasenia2) throws ErrorServicio {
		
		try {
			
			
			usuarioServicio.registro(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia, false);
			modelo.put("exito", "registro exitoso");
			return "inicio.html";
			
		}catch(Exception e){
			modelo.put("error", e.getMessage());
				return "registro-alumno.html";		
		}

	}
	
	
	@GetMapping("/perfilalumno/{id}")
	public String perfilAlumno(ModelMap modelo, @PathVariable("id")String id) {
		
		return "perfilAlumno.html";
	}
	
	@PostMapping("/perfilalumno/{id}")
	public String subirFoto(ModelMap modelo, MultipartFile archivo, @PathVariable("id")String id) throws ErrorServicio {
		
		try {
			usuarioServicio.subirFoto(archivo, id);
		} catch (ErrorServicio ex) {
			modelo.put("error", ex.getMessage());
			
		}
		
		return "perfilAlumno.html";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@GetMapping("/perfilprofesor/{id}")
	public String perfilProfesor(ModelMap modelo, @PathVariable("id")String id) {
	
		return "perfilProfesor.html";
	}
	
	@PostMapping("/perfilprofesor/{id}")
	public String perfilProfesor() {
	
		return "perfilProfesor.html";
	}
	
	

//	
//	EN EL METODO DE USUARIOSERVICIO LOADUSERBYUSERNAME ESE PERMISO. VIDEO 7MINUTO 2.18
	
	
	
	

	
	
	

	
	
	
	
	

}
