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
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping
public class CursoControlador {
	
	@Autowired
	private CursoServicio cursoSer; 


		@GetMapping("/busqueda")
		public String busqueda(ModelMap modelo) {
			modelo.addAttribute("cursos", cursoSer.listarCurso());
			
			return "busqueda.html";
		}
		
		@PostMapping("/busqueda-alerta")
		public String busquedaAlerta(String idAlumno, String idCurso) {
			
			
			
			
			cursoSer.alertaProfesor(idAlumno, idCurso);
						
			
			return "busqueda.html";
		}
		
		
		
		
		
		@GetMapping("/listado-cursos")
		public String listadoCursos(ModelMap modelo, String idProfesor) {
						
			modelo.addAttribute("cursos",cursoSer.cursosProfesor(idProfesor));
						
			return "perfilDocente.html";
		}
		
		@GetMapping("/listado-cursos-cargar")
		public String cargarCursos() {
					
			return "listado-cursos.html";
		}
		
		@PostMapping("/listado-cursos-cargar")
		public String cargarCursos(ModelMap modelo, @RequestParam(required = false) String error, String nombreUsuario, String titulo, Boolean altaBaja, 
				Double precioPorHora, String nivelDificultad, String descripcion, Lenguajes lenguajes) throws ErrorServicio {
			
			cursoSer.crearCurso(nombreUsuario, titulo, altaBaja, precioPorHora, nivelDificultad, descripcion, lenguajes);
			
			if(error != null) {
			
				modelo.put("error", "El curso no se ha cargado correctamente");
				
			}else {
			
				modelo.put("exito", "El curso se ha cargado correctamente");
			}

			
			return "perfilDocente.html";
		}
		
		@PostMapping("/listado-curso-borrar")
		public String borrarCursos(String idCurso) throws ErrorServicio {
			
			cursoSer.bajaCurso(idCurso);
			
			return "perfilDocente.html";
		}
		
		
		
		
		
}

	