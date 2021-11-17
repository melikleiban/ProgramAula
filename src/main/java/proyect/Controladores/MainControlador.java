package proyect.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import proyect.ErrorServicio.ErrorServicio;
import proyect.Servicios.UsuarioServicio;

@Controller
@RequestMapping("/")
public class MainControlador {
	
	
	@Autowired
	private UsuarioServicio usuarioServicio;
		
	@GetMapping("/")
	public String index() {
		return "index.html";
	}
	

	@GetMapping("/login")

	public String login(@RequestParam(required = false) String error, ModelMap modelo) {
		if(error!=null) {
			modelo.put("error", "Contrasenia incorrecta");
		}
		return "login.html";
	}

	public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap modelo) {

		//	HAY QUE CONECTARLO CON EL HTML.  VER EN VIDEO 6 MINUTO 8	
	if(error != null) {
		modelo.put("error", "Nombre de usuario o clave incorrectos");
		}
	
	//	HAY QUE CONECTARLO CON EL HTML.  VER EN VIDEO 6 MINUTO 14.33
	if(error != null) {
		modelo.put("logout", "Ha salido correctamente");
		}
	return "login.html";
	}
	
	
//	@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
//	@GetMapping("/inicio")
//	public String inicio() {
//		return "inicio.html";
//	}
	
	
	@GetMapping("/terminos")
	public String terminos() {
		return "terminos.html";
	}


	@GetMapping("usuario/registro")
	public String registro() {
		return "registro-alumno-profesor.html";
	}	
	
	
	@GetMapping("usuario/registro/profesor")
	public String registroProfesor() {
		return "registro-profesor.html";
	}
	
	
	@PostMapping("usuario/registro/profesor")
	public String registroProfesor(ModelMap modelo,
			@RequestParam(required = false) String nombreUsuario,
			@RequestParam(required = false) String nombreCompleto,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String telefono,
			@RequestParam(required = false) String localidad,
			@RequestParam(required = false) String descripcion,
			@RequestParam(required = false) String contrasenia) throws ErrorServicio{
			//@RequestParam(required = false) String contrasenia2
		try {	
			usuarioServicio.registro(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia, true);
			modelo.put("exito", "registro exitoso");
			return "index.html";
		}catch(Exception e){
			modelo.put("error", e.getMessage());			
				return "registro-profesor.html";		
		}}

	
	 
	@GetMapping("usuario/registro/alumno")
	public String registroAlumno() {
		return "registro-alumno.html";
	}
		
	@PostMapping("usuario/registro/alumno")
	public String registroAlumno(ModelMap modelo,
			@RequestParam(required = false) String nombreUsuario,
			@RequestParam(required = false) String nombreCompleto,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String telefono,
			@RequestParam(required = false) String localidad,
			@RequestParam(required = false) String contrasenia
			) throws ErrorServicio {
		try {			
			usuarioServicio.registro(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia, false);
			modelo.put("exito", "registro exitoso");
			return "index.html";
			}
		catch(Exception e){
			modelo.put("error", e.getMessage());
				return "registro-alumno.html";		
		}
	}

}
