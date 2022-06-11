package br.com.serratec.ecommerce.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.serratec.ecommerce.models.Usuario;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	public static final int TOKEN_EXPIRATION = 	600_000;
	public static final String TOKEN_SECRET = "f7471182-24a2-47d8-babd-95451be9f3bf";
	private final AuthenticationManager authenticationManager;


	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getSenha(), new ArrayList<>());
			return authenticationManager.authenticate(authToken);
		}catch(IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserSS userSS = (UserSS) authResult.getPrincipal();
		String token = JWT.create()
				.withSubject(userSS.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
				.sign(Algorithm.HMAC512(TOKEN_SECRET));
		
		response.addHeader("Authorization", token);;
		response.getWriter().flush();
	}
}
