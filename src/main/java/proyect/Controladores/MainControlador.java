package proyect.Controladores;

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
	public String login(@RequestParam(required = false) String error, ModelMap modelo) {
		if(error!=null) {
			modelo.put("error", "Contrasenia incorrecta");
		}
		return "login.html";
	}
	

	@GetMapping("/terminos")
	public String terminos() {
		return "terminos.html";
	}
	


}
