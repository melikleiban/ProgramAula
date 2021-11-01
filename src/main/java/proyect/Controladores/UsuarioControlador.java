package proyect.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import proyect.ErrorServicio.ErrorServicio;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
	
//	@Autowired
//	private UsuarioServicio usuarioServicio;
	
	@GetMapping("/registro")
	public String registro() {
		return "registro.html";
	}
	
	@PostMapping("/registro")
	public String registro(ModelMap modelo,
			@RequestParam(required = false) String nombreUsuario,
			@RequestParam(required = false) String nombreCompleto,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String telefono,
			@RequestParam(required = false) String localidad,
			@RequestParam(required = false) String descripcion) throws ErrorServicio {
		try {
//			usuarioServicio.registro(nombre);
			modelo.put("exito", "registro exitoso");
			return "redirect:/";
			
		}catch(Exception e){
			modelo.put("error", e.getMessage());
				return "registro.html";		
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
