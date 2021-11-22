package proyect.Configuraciones;

	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import proyect.Servicios.UsuarioServicio;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(99)                                                        
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public UsuarioServicio usuarioServicio;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(usuarioServicio)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
<<<<<<< HEAD
		http
			.authorizeRequests()
			.antMatchers("/css/*", "/js/*", "/img/*", "/*")
			.permitAll()
		.and().formLogin()
				.loginPage("/login")
					.loginProcessingUrl("/logincheck")
					.usernameParameter("nombreUsuario")
					.passwordParameter("clave")
					.defaultSuccessUrl("/")
					.failureUrl("/login?error=error")
					.permitAll()
				.and().logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login")
					.permitAll()
				.and().csrf()
					.disable();
=======
		http.authorizeRequests().antMatchers("/css/*", "/js/*", "/img/*", "/*").permitAll().and().formLogin()
		///ver video 6 minuto 3.15. ese logincheck tiene q estar en el html
				.loginPage("/login").loginProcessingUrl("/logincheck").usernameParameter("nombreUsuario")
				.passwordParameter("clave").defaultSuccessUrl("/").failureUrl("/login?error=error")
				.permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll().and().csrf()
				.disable();
>>>>>>> cf906d43c5b07353ecc6ab7aa445a59c479c1d50
	}
}    