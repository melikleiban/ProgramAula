package proyect.Controladores;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import proyect.Entidades.Usuario;
//import org.springframework.web.multipart.MultipartFile;
import proyect.ErrorServicio.ErrorServicio;
import proyect.Repositorios.UsuarioRepositorio;
import proyect.Servicios.UsuarioServicio;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@RequestMapping("/usuario")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@GetMapping("/perfilalumno")
	public String perfilAlumno() {
		
		return "perfilAlumno.html";
	}

	@GetMapping("/perfilalumno/editar")
	public String perfilAlumnoEditar(@RequestParam String id, ModelMap modelo) {
		
		try {
			Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
			
			if(respuesta.isPresent()) {
			Usuario usuario = respuesta.get();
			modelo.addAttribute("perfilAlumnoEditar", usuario);
			}
			
		}catch (Exception e){
			modelo.put("error", e.getMessage());
		}
		return "perfilAlumnoEditar.html";
	}
	
	@PostMapping("/perfilalumno/editar")
	public String perfilAlumnoEditar(ModelMap modelo,
			String nombreUsuario, 
			String nombreCompleto,
			String email,
			String telefono,	
			String localidad,
			String contrasenia,
			String descripcion,
			MultipartFile archivo,
			@RequestParam String id,
			HttpSession session)
					throws ErrorServicio {
	
			try {
			
			Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
			
			if(respuesta.isPresent()) {
			
			Usuario usuario = respuesta.get();
			
			usuarioServicio.modificar(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia, descripcion, archivo);
			session.setAttribute("usuariosession", usuario);
			
			return "index.html";
			
			}
			
		} catch (ErrorServicio ex) {
			
			modelo.put("error", ex.getMessage());
		}
		return "perfilAlumno.html";
	}
	
	@GetMapping("/perfilprofesor")
	public String perfilProfesor() {
		
		return "perfilProfesor.html";
	}

	@GetMapping("/perfilprofesor/editar")
	public String perfilProfesorEditar(@RequestParam String id, ModelMap modelo) {
		
		try {
			Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
			
			if(respuesta.isPresent()) {
			Usuario usuario = respuesta.get();
			modelo.addAttribute("perfilProfesorEditar", usuario);
			}
			
		}catch (Exception e){
			modelo.put("error", e.getMessage());
		}
		return "perfilProfesorEditar.html";
	}
	
	@PostMapping("/perfilprofesor/editar")
	public String perfilProfesorEditar(ModelMap modelo,
			String nombreUsuario, 
			String nombreCompleto,
			String email,
			String telefono,	
			String localidad,
			String contrasenia,
			String descripcion,
			MultipartFile archivo,
			@RequestParam String id,
			HttpSession session)
					throws ErrorServicio {
	
			try {
			
			Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
			
			if(respuesta.isPresent()) {
			
			Usuario usuario = respuesta.get();
			
			usuarioServicio.modificar(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia, descripcion, archivo);
			session.setAttribute("usuariosession", usuario);
			
			return "index.html";
			
			}
			
		} catch (ErrorServicio ex) {
			
			modelo.put("error", ex.getMessage());
		}
		return "perfilProfesor.html";
	}
	


}
