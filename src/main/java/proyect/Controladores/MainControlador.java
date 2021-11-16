package proyect.Controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainControlador {
		
	@GetMapping("/")
	public String index() {
		return "index.html";
	}
	

	@GetMapping("/login")
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
	
	
	@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
	@GetMapping("/inicio")
	public String inicio() {
		return "inicio.html";
	}
	
	@GetMapping("/terminos")
	public String terminos() {
		return "terminos.html";
	}
	

	


}
