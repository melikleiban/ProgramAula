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

		@GetMapping("/busqueda")
		public String busqueda(ModelMap modelo) {
			modelo.put("cursos", cursoSer.listarCurso());
			return "busqueda.html";
		}
		
		
		
}

	