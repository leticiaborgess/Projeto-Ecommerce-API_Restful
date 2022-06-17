package br.com.serratec.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AuthService authService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.csrf()
			.disable();
		
		http
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/login", "/cliente").permitAll()
			.antMatchers(HttpMethod.GET, "/endereco/**").hasRole("cliente")
			.antMatchers(HttpMethod.GET, "/categoria/**", "/produto/**", "pedido/**").hasAnyRole("cliente", "funcionario")
			.antMatchers(HttpMethod.GET, "/funcionario/**").hasRole("funcionario")
			.antMatchers(HttpMethod.POST, "/pedido", "/endereco").hasRole("cliente")
			.antMatchers(HttpMethod.POST, "/categoria", "/produto", "/funcionario").hasRole("funcionario")
			.antMatchers(HttpMethod.PUT, "/cliente/**", "/pedido/**", "/endereco/**").hasRole("cliente")
			.antMatchers(HttpMethod.PUT, "/categoria/**", "/produto/**", "/funcionario/**").hasRole("funcionario")
			.antMatchers(HttpMethod.DELETE, "/cliente", "/endereco/**").hasRole("cliente")
			.antMatchers(HttpMethod.DELETE, "/funcionario/**", "/categoria/**", "/produto/**").hasRole("funcionario")
			.anyRequest()
			.authenticated()
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager()));

		http
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(bCryptPasswordEncoder);
	}
}
