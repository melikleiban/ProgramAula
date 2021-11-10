package proyect.Servicios;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class NotificacionServicio{
	
	
	@Autowired(required = true)
	private JavaMailSender mailSender;
	
	@Async
	public void enviar(String cuerpo, String titulo, String mail) {
		SimpleMailMessage mensaje = new SimpleMailMessage();
		mensaje.setTo(mail);
		mensaje.setFrom("programaula2021@gmail.com");
		mensaje.setSubject(titulo);
		mensaje.setText(cuerpo);
		
		mailSender.send(mensaje);
	}
<<<<<<< HEAD
	
	}
=======

	
}
>>>>>>> 122c79312751c4138117f75cce7ee486509c7734
