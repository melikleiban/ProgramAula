package proyect.Controladores;

import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping
public class CursoControlador {

		@GetMapping("/busqueda")
		public String busqueda() {
			return "busqueda.html";
		}
}

	
	
	

=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CursoControlador {

	@GetMapping("/busqueda")
	public String busqueda() {
		return "busqueda.html";
	}
}
>>>>>>> ca690033ad93b391e60849d57ade3642610b122c
