package proyect.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;


public class NotificacionServicio {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Async
	public void enviar(String cuerpo, String titulo, String mail) {
		SimpleMailMessage mensaje = new SimpleMailMessage();
		mensaje.setTo(mail);
		mensaje.setFrom("noreply@programaula.com");
		mensaje.setSubject(titulo);
		mensaje.setText(cuerpo);
		
		mailSender.send(mensaje);
	}
}
