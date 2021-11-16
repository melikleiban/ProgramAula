package proyect.Configuraciones;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracionEmail {
	

	@Bean
    public JavaMailSender getJavaMailSender() 
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(25);
          
        mailSender.setUsername("programaula2021@gmail.com");
        mailSender.setPassword("yhrqdkqmzqxpgwbz");
          
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
          
        return mailSender;
    }
     
    public void emailSender(String cuerpo, String titulo, String mail)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail);
        message.setFrom("noreply@programaula.com");
        message.setSubject(titulo);
        message.setText(cuerpo);
        
        getJavaMailSender().send(message);
    }
    
}