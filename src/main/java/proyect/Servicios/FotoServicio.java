package proyect.Servicios;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import proyect.Entidades.Foto;
import proyect.ErrorServicio.ErrorServicio;
import proyect.Repositorios.FotoRepositorio;

@Service
public class FotoServicio {

	@Autowired
	private FotoRepositorio fotoRepositorio;
	
	@Transactional
	public Foto guardarFoto(MultipartFile archivo) throws ErrorServicio {
		if (archivo != null) {
			try {
				Foto foto = new Foto();
				foto.setNombre(archivo.getName());
				foto.setMime(archivo.getContentType());
				foto.setContenido(archivo.getBytes());

				return fotoRepositorio.save(foto);
			} catch (Exception e) {
				System.err.print(e.getMessage());
			}
		}
		return null;
	}
	
	@Transactional
	public Foto actualizarFoto(String id, MultipartFile archivo) throws ErrorServicio {
		if (archivo != null) {
			try {
				Foto foto = new Foto();

				if(id != null) {
					Optional<Foto> fotoActual = fotoRepositorio.findById(id);
					if (fotoActual.isPresent()) {
						foto = fotoActual.get();	
					}
				}

				foto.setNombre(archivo.getName());
				foto.setMime(archivo.getContentType());
				foto.setContenido(archivo.getBytes());

				return fotoRepositorio.save(foto);
			} catch (Exception e) {
				System.err.print(e.getMessage());
			}
		}
		return null;
	}
}
