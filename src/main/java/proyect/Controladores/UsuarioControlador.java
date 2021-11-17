package proyect.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestParam;

import proyect.Entidades.Usuario;
=======
//import org.springframework.web.bind.annotation.RequestParam;
>>>>>>> origin/Pamela
import org.springframework.web.multipart.MultipartFile;

//import org.springframework.web.multipart.MultipartFile;
import proyect.ErrorServicio.ErrorServicio;
import proyect.Servicios.UsuarioServicio;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@RequestMapping("/usuario")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	
<<<<<<< HEAD
	@GetMapping("/registro")
	public String registro() {
		return "registro-alumno-profesor.html";
	}	
	
	
	@GetMapping("/registro/profesor")
	public String registroProfesor() {
		return "registro-profesor.html";
	}
	
	
	@PostMapping("/registro/profesor")
	public String registroProfesor(ModelMap modelo,
			@RequestParam(required = false) String nombreUsuario,
			@RequestParam(required = false) String nombreCompleto,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String telefono,
			@RequestParam(required = false) String localidad,			
			@RequestParam(required = false) String contrasenia
			) throws ErrorServicio {
		
		try {
			
			usuarioServicio.registro(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia, true);
			modelo.put("exito", "registro exitoso");
			return "index.html";
			
		}catch(Exception e){
			modelo.put("error", e.getMessage());			
				return "registro-profesor.html";		
		}

	}
	 
	@GetMapping("/registro/alumno")
	public String registroAlumno() {
		return "registro-alumno.html";
	}
		
	@PostMapping("/registro/alumno")
	public String registroAlumno(ModelMap modelo,
			@RequestParam(required = false) String nombreUsuario,
			@RequestParam(required = false) String nombreCompleto,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String telefono,
			@RequestParam(required = false) String localidad,
			@RequestParam(required = false) String contrasenia) throws ErrorServicio {
		
		try {
			
			
			usuarioServicio.registro(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia, false);

			modelo.put("exito", "Registro exitoso!!!");
			modelo.addAttribute(nombreCompleto, nombreCompleto);
			return "login.html";
			
		}catch(Exception e){
			modelo.put("error", "Error al Registrarse!!!");
			modelo.put("nombreUsuario", nombreUsuario);
			modelo.put("nombreCompleto", nombreCompleto);
			modelo.put("email", email);
			modelo.put("telefono", telefono);
				return "registro-alumno.html";			
		}
	}
	
=======
>>>>>>> origin/Pamela
	@GetMapping("/perfilalumno")
	public String perfilAlumno() {
		
		return "perfilAlumno.html";
	}
	
<<<<<<< HEAD

	
	@GetMapping("/perfilalumno/editar/{id}")
	public String perfilAlumnoEditar(ModelMap modelo, @PathVariable("id")String id) {
=======
	@GetMapping("/perfilalumno/editar")
	public String perfilAlumnoEditar() {
>>>>>>> origin/Pamela
		
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
			MultipartFile archivo)
					throws ErrorServicio {
		
		try {
			
			usuarioServicio.modificar(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia, descripcion, archivo);
		
			
		} catch (ErrorServicio ex) {
			modelo.put("error", ex.getMessage());
			
		}
		
		return "perfilAlumno.html";
		
	}
<<<<<<< HEAD
	
	

	
=======
>>>>>>> origin/Pamela

	@GetMapping("/perfildocente")
	public String perfilProfesor() {
	
		return "perfilDocente.html";
	}
	
<<<<<<< HEAD
	
	
	@GetMapping("/logout")
	public String logout() {
		return "logout.html";
	}
	
	
		
	
	
	
=======
	@PostMapping("/perfilprofesor/editar")
	public String perfilProfesorEditar(
				ModelMap modelo,
				String nombreUsuario, 
				String nombreCompleto,
				String email,
				String telefono,	
				String localidad,
				String contrasenia,
				String descripcion,
				MultipartFile archivo)
						throws ErrorServicio {
			try {
				
				usuarioServicio.modificar(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia, descripcion, archivo);
				
			} catch (ErrorServicio ex) {
				modelo.put("error", ex.getMessage());
			}
	
		return "perfilDocente.html";
	}

//	
//	EN EL METODO DE USUARIOSERVICIO LOADUSERBYUSERNAME ESE PERMISO. VIDEO 7MINUTO 2.18
>>>>>>> origin/Pamela

}
