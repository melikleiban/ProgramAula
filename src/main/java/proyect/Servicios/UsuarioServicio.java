package proyect.Servicios;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import proyect.Configuraciones.ConfiguracionEmail;
import proyect.Entidades.Foto;
import proyect.Entidades.Usuario;
import proyect.Enums.Rol;
import proyect.ErrorServicio.ErrorServicio;
import proyect.Repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private FotoServicio fotoServicio;
	
	@Autowired
	private ConfiguracionEmail configuracionEmail;
	
	@Transactional
	public void registro(String nombreUsuario, 
			String nombreCompleto,
			String email,
			String telefono,
			String localidad,
			String contrasenia,
			Boolean rol) throws ErrorServicio {	

		validar(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia);
		
		
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(nombreUsuario);
		usuario.setNombreCompleto(nombreCompleto);
		usuario.setEmail(email);
		usuario.setTelefono(telefono);
		usuario.setLocalidad(localidad);
		usuario.setAltaBaja(true);
		

		

		




		String contraEncriptada = new BCryptPasswordEncoder().encode(contrasenia);
		usuario.setContrasenia(contraEncriptada);
		
		
		if(rol == true) {
			usuario.setRol(Rol.PROFESOR);
		} else { 
			usuario.setRol(Rol.ALUMNO);
		}

		try {
			
			usuarioRepositorio.save(usuario);
			
			//configuracionEmail.emailSender(registroExitosoMensaje(email,contrasenia,nombreCompleto), "Registro ProgramAula", email);
			
		} catch( Exception e ) {
			e.printStackTrace();
			System.out.println("Entra al catch en servicio");
		}
		
	}

	@Transactional
	public void modificar(String nombreUsuario, 
			String nombreCompleto,
			String email,
			String telefono,
			String localidad,
			String contrasenia,
			String descripcion,
			MultipartFile foto) throws ErrorServicio {	

		validar(nombreUsuario, nombreCompleto, email, telefono, localidad, contrasenia);

		Usuario usuario = usuarioRepositorio.buscarPorNombreUsuario(nombreUsuario);
		usuario.setNombreCompleto(nombreCompleto);
		usuario.setEmail(email);
		usuario.setTelefono(telefono);
		usuario.setLocalidad(localidad);

		String idFoto = usuario.getFoto().getId();
		Foto nuevaFoto = fotoServicio.actualizarFoto(idFoto, foto);
		usuario.setFoto(nuevaFoto);
		
		
		String contraEncriptada = new BCryptPasswordEncoder().encode(contrasenia);
		usuario.setContrasenia(contraEncriptada);

		try {
			usuarioRepositorio.save(usuario);
		} catch( Exception e ) {
			e.printStackTrace();;
		}
		
	}

	
	public void desactivarCuenta(String nombreUsuario) {
		Usuario usuario = usuarioRepositorio.buscarPorNombreUsuario(nombreUsuario);
		usuario.setAltaBaja(false);
		
		usuarioRepositorio.save(usuario);
	}
	
	public void validar(String nombreUsuario, 
			String nombreCompleto,
			String email,
			String telefono,
			String localidad,
			String contrasenia
			)throws ErrorServicio{

		if(nombreUsuario==null || nombreUsuario.isEmpty()) {
			throw new ErrorServicio ("El nombre de usuario no puede ser nulo/vacío.");
		}
		if(nombreCompleto==null || nombreCompleto.isEmpty() || nombreCompleto.length() < 3 ) {
			throw new ErrorServicio ("El nombre de la persona no puede estar vacío o contener menos de 3 letras.");
		}
		if(email==null || email.isEmpty() || email.contains(" ") || !email.contains("@") || !email.contains(".com")) {
			throw new ErrorServicio ("El email debe ser válido.");
		}
		if(telefono==null || telefono.isEmpty() || UsuarioServicio.esNumero(telefono) == false) {
			throw new ErrorServicio ("El numero de teléfono debe ser válido.");
		}
		if(localidad==null || localidad.isEmpty()) {
			throw new ErrorServicio ("La localidad no puede estar vacía.");
		}
		if(contrasenia==null || contrasenia.isEmpty() || contrasenia.contains(" ") || contrasenia.length() < 8 || contrasenia.length() > 12) {
			throw new ErrorServicio ("La contraseña debe poseer entre 8 y 12 caracteres válidos.");
		}

	}

	@SuppressWarnings("unused")
	public static boolean esNumero(String strNumero) {
		if (strNumero == null) {
			return false;
		}

		try {
			Long l = Long.parseLong(strNumero);
		} catch (NumberFormatException nfe) {
			return false;
		}

		return true;
	}


	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepositorio.buscarPorNombreUsuario(nombreUsuario);
		
		if (usuario == null) {
			
			return null;
			
		}
			
			List<GrantedAuthority> permisos = new ArrayList<>();

			GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_"+ usuario.getRol());
						
			permisos.add(p1);
			
			GrantedAuthority p2 = new SimpleGrantedAuthority("ROLE_USUARIO_REGISTRADO");
			
			permisos.add(p2);
			
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			
			HttpSession session = attr.getRequest().getSession(true);
			
			session.setAttribute("usuariosession", usuario);
			
			return new User(usuario.getNombreUsuario(), usuario.getContrasenia(), permisos);
				

			
			} 
	
		
		
		
		
		
		
		
		
		
//		if (usuario != null && usuario.getRol() == Rol.PROFESOR) {
//			List<GrantedAuthority> permisos = new ArrayList<>();
//
//			GrantedAuthority p1 = new SimpleGrantedAuthority("MODULO_CURSOS");
//			permisos.add(p1);
//			GrantedAuthority p2 = new SimpleGrantedAuthority("MODULO_FOTO");
//			permisos.add(p2);
//
//			User user = new User(usuario.getNombreUsuario(), usuario.getContrasenia(), permisos);
//
//			return user;
//		}
//		if (usuario != null && usuario.getRol() == Rol.ALUMNO) {
//			List<GrantedAuthority> permisos = new ArrayList<>();
//
//			
//			GrantedAuthority p1 = new SimpleGrantedAuthority("MODULO_CURSOS");
//			permisos.add(p1);
//			GrantedAuthority p2 = new SimpleGrantedAuthority("MODULO_FOTO");
//			permisos.add(p2);
//
//			User user = new User(usuario.getNombreUsuario(), usuario.getContrasenia(), permisos);
//
//			return user;

		
	
	
	public String registroExitosoMensaje(String nombreUsuario, String contrasenia, String nombreCompleto) {
		
		String registroExitoso = 
		"Bienvenido a ProgramAula " + nombreCompleto + "! "
		+ System.lineSeparator()
		+"Su nombre de usuario es: " + nombreUsuario
		+ System.lineSeparator()
		+"Su contraseña es: " + contrasenia
		+ System.lineSeparator()
		+"Esperamos que pueda sacar provecho y enriquecer sus conocimientos con "
		+"otros usuarios de la web."
		+ System.lineSeparator()
		+ "Si usted no se registró, por favor, desestime este mensaje.";
		
		return registroExitoso;
	}
	
	public Usuario buscarPorNombreUsuario(String nombreUsuario) {
		
		Usuario us = usuarioRepositorio.buscarPorNombreUsuario(nombreUsuario);
		return us;
	}
}
