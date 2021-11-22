package proyect.Controladores;

<<<<<<< HEAD
//import javax.servlet.http.HttpSession;
=======
import java.util.Optional;

import javax.servlet.http.HttpSession;
>>>>>>> cf906d43c5b07353ecc6ab7aa445a59c479c1d50

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
<<<<<<< HEAD
import org.springframework.web.multipart.MultipartFile;

=======
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import proyect.Entidades.Curso;
import proyect.Entidades.Usuario;
>>>>>>> cf906d43c5b07353ecc6ab7aa445a59c479c1d50
//import org.springframework.web.multipart.MultipartFile;
import proyect.ErrorServicio.ErrorServicio;
import proyect.Repositorios.CursoRepositorio;
import proyect.Repositorios.UsuarioRepositorio;
import proyect.Servicios.CursoServicio;
import proyect.Servicios.UsuarioServicio;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@RequestMapping("/usuario")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private CursoServicio cursoServicio;
	
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
			
<<<<<<< HEAD
			usuarioServicio.registro(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia,true);
			modelo.put("exito", "Registro exitoso!!!");
			return "login.html";
			
		}catch(Exception e){
			modelo.put("error", "Error al Registrarse!!!");	
			modelo.put("nombreUsuario", nombreUsuario);
			modelo.put("nombreCompleto", nombreCompleto);
			modelo.put("email", email);
			modelo.put("telefono", telefono);
				return "registro-profesor.html";		
=======
			if(respuesta.isPresent()) {
			Usuario usuario = respuesta.get();
			modelo.addAttribute("perfilAlumnoEditar", usuario);
			}
			
		}catch (Exception e){
			modelo.put("error", e.getMessage());
>>>>>>> cf906d43c5b07353ecc6ab7aa445a59c479c1d50
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
<<<<<<< HEAD

	}
	

	@GetMapping("/perfilAlumno")
	public String perfilAlumno() {
		System.out.println("Entra al controller");
	return "perfilAlumno.html";
	}
	

	
	@GetMapping("/perfilalumno/editar/{id}")
	public String perfilAlumnoEditar(ModelMap modelo, @PathVariable("id")String id) {
		
		return "perfilAlumnoEditar.html";
	}
	
	@PostMapping("/perfilalumno/editar/{id}")
	public String subirFoto(ModelMap modelo,
			String nombreUsuario, 
			String nombreCompleto,
			String email,
			String telefono,
			String localidad,
			String contrasenia,
			String descripcion,
			MultipartFile archivo, 
			@PathVariable("id")String id) throws ErrorServicio {
		
	//	try {			
			//usuarioServicio.modificar(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia, descripcion, archivo);		
			
	//	} catch (ErrorServicio ex) {
		//	modelo.put("error", ex.getMessage());
			
	//	}		
		return "perfilAlumno.html";		
	}
	


	@GetMapping("/perfilDocente")
	public String perfilProfesor() {	
=======
		return "perfilAlumno.html";
	}
	
	@GetMapping("/perfilprofesor/{id}")
	public String perfilProfesor(ModelMap modelo,@PathVariable ("id") String profesor_id ) {
		
		
		List <Curso> cursos = cursoServicio.cursosProfesor(profesor_id);
		modelo.addAttribute("cursos", cursos);

>>>>>>> cf906d43c5b07353ecc6ab7aa445a59c479c1d50
		return "perfilDocente.html";
	}

	
	
	
	
	
	
<<<<<<< HEAD
	
	
	
	@GetMapping("/login")
	public String login(@RequestParam(required = false) String error,@RequestParam(required = false) String logout, ModelMap modelo) {
		
		modelo.put("error", "Nombre de Usuario o clave Incorrectos");
		
		
		modelo.put("logout", "Nombre de Usuario o clave Incorrectos");
		
		return "login.html";
	}
	
//	@PostMapping("/logearse")
//	public String logearse(@RequestParam String usuario, @RequestParam String contrasenia) {
//		System.out.println("Usuario: " + usuario);
//		System.out.println("Clave: " + contrasenia);
//		
//		return "index.html";
//	}
=======
//	@GetMapping("/perfilprofesor/cursos")
//	public String perfilProfesorCursos(ModelMap modelo, @RequestParam ("id") String id) {
//		
//		System.out.println(id);
//		modelo.addAttribute("cursos",cursoServicio.cursosProfesor(id));
//		
//		return "perfilDocente.html";
//	}

	
	
	

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
>>>>>>> cf906d43c5b07353ecc6ab7aa445a59c479c1d50
	
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