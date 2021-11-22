package proyect.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import proyect.Enums.Lenguajes;
import proyect.ErrorServicio.ErrorServicio;
import proyect.Servicios.CursoServicio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
public class CursoControlador {
	
	@Autowired
	private CursoServicio cursoSer; 


		@GetMapping("/busqueda")
		public String busqueda(ModelMap modelo) {
			modelo.addAttribute("cursos", cursoSer.listarCurso());
			
			return "busqueda.html";
		}
		
		@GetMapping("/busqueda/palabraClave")
		public String filtroBusqueda(ModelMap modelo, @RequestParam String palabraClave) {
			
			System.out.println(palabraClave);
			modelo.addAttribute("cursos", cursoSer.listarCursoPorPalabraClave(palabraClave));
			
			return "busqueda.html";
		}
			
		
		
		@GetMapping("/busqueda-alerta")
		public String busquedaAlerta() {				
			return "index.html";
		}
		
		@PostMapping("/busqueda-alerta")
		public String busquedaAlerta(String idAlumno, String idCurso, String mensaje) {				
			
			System.out.println("id alumno" + idAlumno + "id curso" + idCurso);
			
			cursoSer.alertaProfesor(idAlumno, idCurso, mensaje);								
			System.out.println("el servicio???");
			return "index.html";
		}
		
		
			
		@GetMapping("/usuario/nuevo-curso")
		public String cargarCursos() {
					
			return "nuevo-curso.html";
		}
		
		@PostMapping("/usuario/nuevo-curso")
		public String cargarCursos(ModelMap modelo, @RequestParam(required = false) String error, 
				String nombreUsuario, String titulo, 
				Double precioPorHora, String nivelDificultad, String descripcion, 
				Lenguajes lenguajes, String id_profesor) throws ErrorServicio {
			
			cursoSer.crearCurso(nombreUsuario, titulo, true, precioPorHora, nivelDificultad, descripcion, lenguajes);			
			String ruta = ("redirect:/usuario/perfilprofesor/" + id_profesor);
			
			return ruta;
		}
		
		@PostMapping("/listado-curso-borrar")
		public String borrarCursos(String idCurso) throws ErrorServicio {
			
			cursoSer.bajaCurso(idCurso);
			
			return "perfilDocente.html";
		}	
						
}
