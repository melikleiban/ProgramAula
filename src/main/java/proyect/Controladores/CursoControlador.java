package proyect.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import proyect.Servicios.CursoServicio;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping
public class CursoControlador {
	
	@Autowired
	private CursoServicio cursoSer; 

<<<<<<< HEAD
	@GetMapping("/busqueda")
	public String busqueda() {
		return "busqueda.html";
	}
	
	
	
	
	
	
=======
		@GetMapping("/busqueda")
		public String busqueda(ModelMap modelo) {
			modelo.addAttribute("cursos", cursoSer.listarCurso());
			return "busqueda.html";
		}
		
		
		
>>>>>>> 7ba6b28c18883c04c07a7effe827718941d375ca
}

	