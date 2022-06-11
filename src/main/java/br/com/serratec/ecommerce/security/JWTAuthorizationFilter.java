package br.com.serratec.ecommerce.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String atributo = request.getHeader("Authorization");
		
		if(atributo == null) {
			chain.doFilter(request, response);
			return;
		}
		
		if(!atributo.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}
		
		String token = atributo.replace("Bearer ", "");
		UsernamePasswordAuthenticationToken authToken = getAuthToken(token);
		
		SecurityContextHolder.getContext().setAuthentication(authToken);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthToken(String token) {
		String usuario = JWT.require(Algorithm.HMAC512(JWTAuthenticationFilter.TOKEN_SECRET))
				.build()
				.verify(token)
				.getSubject();
		
		if(usuario == null) {
			return null;
		}
		
		return new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<>());
	}
}
