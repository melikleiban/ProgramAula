package proyect.Controladores;

import java.util.logging.Level;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import proyect.Entidades.Usuario;
import proyect.ErrorServicio.ErrorServicio;
import proyect.Servicios.UsuarioServicio;

@Controller
@RequestMapping("/foto")
public class FotoControlador {
	
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@GetMapping("/usuario")
	public ResponseEntity<byte[]> fotoUsuario(@RequestParam String nombreUsuario){
		
		try {
			Usuario usuario = usuarioServicio.buscarPorNombreUsuario(nombreUsuario);
			if(usuario.getFoto() == null) {
				throw new ErrorServicio("El usuario no tiene foto");
			}
			
			byte[] foto = usuario.getFoto().getContenido();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			return new ResponseEntity<>(foto, headers, HttpStatus.OK);
		}catch (ErrorServicio ex){
			Logger.getLogger(FotoControlador.class.getName()).log(null, Level.SEVERE, null, ex);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
				
			}
	
}
